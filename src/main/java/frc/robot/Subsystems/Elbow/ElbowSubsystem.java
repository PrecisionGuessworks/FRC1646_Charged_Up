// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Elbow;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.fasterxml.jackson.databind.util.RawValue;

import edu.wpi.first.math.filter.Debouncer;
import edu.wpi.first.math.filter.MedianFilter;
import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.filter.Debouncer.DebounceType;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.simulation.AnalogInputSim;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import frc.robot.constants.RobotMap;
import frc.robot.constants.Constants.ArmConstants;
import frc.robot.lib.TalonFXFactory;

public class ElbowSubsystem extends SubsystemBase {

  private static ElbowSubsystem instance;
  private TalonFX elbowLeftMotor, elbowRightMotor;
  private SlewRateLimiter elbowFilter;
  
  private MedianFilter encoderFilter;
  private Debouncer limitSwitchDebouncer;

  private DigitalInput limitSwitch;

  private double snapshotEncoder, elbowFoldLimit;


  private ElbowSubsystem() {
    // Stage 1 to 2
    elbowLeftMotor = TalonFXFactory.makeTalonFX(RobotMap.ELBOW_LEFT_ID); 
    elbowRightMotor = TalonFXFactory.makeTalonFX(RobotMap.ELBOW_RIGHT_ID);

    elbowLeftMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    elbowRightMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);

    elbowLeftMotor.setInverted(TalonFXInvertType.CounterClockwise);
    elbowRightMotor.setInverted(TalonFXInvertType.Clockwise);

    elbowLeftMotor.setNeutralMode(NeutralMode.Brake);
    elbowRightMotor.setNeutralMode(NeutralMode.Brake);


    // Extra Sensors
    limitSwitch = new DigitalInput(RobotMap.ELBBOW_LIMIT_SWITCH_ID); // 

    // Value Normalizing
    elbowFilter = new SlewRateLimiter(ArmConstants.ELBOW_SLEW_RATE_LIMIT);
    encoderFilter = new MedianFilter(50);
    limitSwitchDebouncer = new Debouncer(ArmConstants.ELBOW_LIMIT_SWITCH_DEBOUNCE_TIME, DebounceType.kBoth);   
  }

  public static ElbowSubsystem getInstance(){
    if (instance == null){
      instance = new ElbowSubsystem();
    }
    return instance;
  }


  public void resetFoldLimit(){
    if (isElbowLimitSwitchTriggered()) {
      elbowFoldLimit = getElbowPosition() + ArmConstants.ELBOW_TRAVEL_DELTA; // TODO: depending signed vals, might need -/+
    }
  }

  public void setSlewedElbowPower(double power){
    power = elbowFilter.calculate(power);
    setRawElbowPower(power);
  }
  public void setRawElbowPower(double power){
    elbowLeftMotor.set(ControlMode.PercentOutput, power);
    elbowRightMotor.set(ControlMode.PercentOutput, power);
  }

  private boolean isElbowTooHigh(double power){
    return isElbowLimitSwitchTriggered() && power > 0.0;
  }
  private boolean isElbowTooLow(double power){
    return power < 0.0 && getElbowPosition() < elbowFoldLimit;
  }

  public void setElbowPowerWithSafeties(double power){
    if (isElbowTooHigh(power)) {
      setRawElbowPower(0);
    } else if (isElbowTooLow(power)) {
      setRawElbowPower(0); // TODO: Be prepared to revert (as a temp change until tweaked)
    } else {
      setSlewedElbowPower(power);
    }
  }
  public void stopElbow(){
    setRawElbowPower(0);
  }

  public void setElbowPositionByEncoder(double position) {
    elbowLeftMotor.set(ControlMode.Position, position);
    elbowRightMotor.set(ControlMode.Position, position);
  }

  public double getElbowPosition() {
    return elbowLeftMotor.getSelectedSensorPosition();
  }

  public void displayElbowPositions(){
    SmartDashboard.putString("Elbow Encoder", getElbowPosition() + "");
  }
  public void displayRequestedPower(String s, double power){
    SmartDashboard.putString(s + " Power: ", power + "");
  }

  public boolean isElbowLimitSwitchTriggered(){
    return !limitSwitchDebouncer.calculate(limitSwitch.get());
  }

  public void displayLimitSwitchStatus(){
    SmartDashboard.putString("Elbow Limit Switch", isElbowLimitSwitchTriggered() + "");
  }
  public void displayEncoderTarget(){
    SmartDashboard.putString("Elbow Encoder Target", elbowFoldLimit + "");
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}