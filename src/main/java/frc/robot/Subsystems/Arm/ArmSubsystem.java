// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Arm;

import javax.print.attribute.standard.Media;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

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

public class ArmSubsystem extends SubsystemBase {

  private static ArmSubsystem instance;
  private TalonFX shoulderLeftMotor, shoulderRightMotor, elbowLeftMotor, elbowRightMotor;
  private AnalogPotentiometer shoulderPot;
  private SlewRateLimiter shoulderFilter, elbowFilter;
  
  private MedianFilter potFilter;
  private Debouncer limitSwitchDebouncer;

  private DigitalInput limitSwitch;

  private double snapshotEncoder, elbowFoldLimit;
  

  private ArmSubsystem() {
    //Stage 0 to 1
    shoulderLeftMotor = TalonFXFactory.makeTalonFX(RobotMap.SHOULDER_LEFT_ID); 
    shoulderRightMotor = TalonFXFactory.makeTalonFX(RobotMap.SHOULDER_RIGHT_ID); 

    // Stage 1 to 2
    elbowLeftMotor = TalonFXFactory.makeTalonFX(RobotMap.ELBOW_LEFT_ID); 
    elbowRightMotor = TalonFXFactory.makeTalonFX(RobotMap.ELBOW_RIGHT_ID);

    shoulderLeftMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    shoulderRightMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);

    elbowLeftMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    elbowRightMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);

    shoulderLeftMotor.setInverted(TalonFXInvertType.CounterClockwise);
    shoulderRightMotor.setInverted(TalonFXInvertType.Clockwise);
    elbowLeftMotor.setInverted(TalonFXInvertType.CounterClockwise);
    elbowRightMotor.setInverted(TalonFXInvertType.Clockwise);


    shoulderLeftMotor.setNeutralMode(NeutralMode.Brake);
    shoulderRightMotor.setNeutralMode(NeutralMode.Brake);
    elbowLeftMotor.setNeutralMode(NeutralMode.Brake);
    elbowRightMotor.setNeutralMode(NeutralMode.Brake);


    // Extra Sensors
    shoulderPot = new AnalogPotentiometer(RobotMap.SHOULDER_POT_LEFT_ID,100, ArmConstants.SHOULDER_POT_OFFSET);
    limitSwitch = new DigitalInput(RobotMap.ELBBOW_LIMIT_SWITCH_ID); // 

    // Value Normalizing
    shoulderFilter = new SlewRateLimiter(ArmConstants.SHOULDER_SLEW_RATE_LIMIT);
    elbowFilter = new SlewRateLimiter(ArmConstants.ELBOW_SLEW_RATE_LIMIT);
    potFilter = new MedianFilter(5); // value is the sample size to take
    limitSwitchDebouncer = new Debouncer(ArmConstants.ELBOW_LIMIT_SWITCH_DEBOUNCE_TIME, DebounceType.kBoth);
  }

  public static ArmSubsystem getInstance(){
    if (instance == null){
      instance = new ArmSubsystem();
    }
    return instance;
  }


  public void resetFoldLimit(){
    if (isElbowLimitSwitchTriggered()) {
      elbowFoldLimit = getElbowPosition() + ArmConstants.ELBOW_TRAVEL_DELTA; // TODO: depending signed vals, might need -/+
    }
  }

  public void setShoulderPower(double power){
    // TODO: Add shoulder filter if desired
    power = shoulderFilter.calculate(power);
    shoulderLeftMotor.set(ControlMode.PercentOutput, power);
    shoulderRightMotor.set(ControlMode.PercentOutput, power);
  }

  private boolean isShoulderTooHigh(double power){
    return power > 0.0 && getShoulderPosition() > ArmConstants.SHOULDER_HIGH_LIMIT;
  }
  private boolean isShoulderTooLow(double power){
    return power < 0.0 && getShoulderPosition() < ArmConstants.SHOULDER_LOW_LIMIT;
  }

  public void setShoulderPowerWithSafeties(double power){
    if (isShoulderTooHigh(power)) { 
      setShoulderPower(0);
    } else if (isShoulderTooLow(power)) {
      setShoulderPower(0);
    } else {
      setShoulderPower(power);
    }
  }

  public void setShoulderPosition(double position){
    shoulderLeftMotor.set(ControlMode.Position, position);
    shoulderRightMotor.set(ControlMode.Position, position);
  }

  public double getShoulderPosition(){
    return cleanPotValue(getShoulderPotValue());
  }

  public double cleanPotValue(double rawValue){
    return potFilter.calculate(rawValue);
  }

  public void setElbowPower(double power){
    power = elbowFilter.calculate(power);
    elbowLeftMotor.set(ControlMode.PercentOutput, power);
    elbowRightMotor.set(ControlMode.PercentOutput, power);
  }

  private boolean isElbowTooHigh(double power){
    return isElbowLimitSwitchTriggered() && power > 0.0;
    //return power > 0.0 && getElbowPosition() > Constants.ArmConstants.ELBOW_HIGH_LIMIT;
  }
  private boolean isElbowTooLow(double power){
    return power < 0.0 && getElbowPosition() < elbowFoldLimit;
  }

  public void setElbowPowerWithSafeties(double power){
    if (isElbowTooHigh(power)) {
      setElbowPower(0);
    } else if (isElbowTooLow(power)) {
      setElbowPower(power); // TODO: Be prepared to revert (as a temp change until tweaked)
    } else {
      setElbowPower(power);
    }
  }

  public void setElbowPosition(double position) {
    elbowLeftMotor.set(ControlMode.Position, position);
    elbowRightMotor.set(ControlMode.Position, position);
  }

  public double getElbowPosition() {
    return elbowLeftMotor.getSelectedSensorPosition();
  }

  public void displayShoulderPosition(){
    SmartDashboard.putString("Shoulder", getShoulderPosition() + "");
  }
  public void displayElbowPosition(){
    SmartDashboard.putString("Elbow", getElbowPosition() + "");
  }

  public void displayArmPositions(){
    displayShoulderPosition();
    displayElbowPosition();
  }

  public void displayShoulderPot(){
    SmartDashboard.putString("Soulder Pot", getShoulderPotValue() + "");
  }

  public double getShoulderPotValue(){
    return shoulderPot.get();
  }

  public void displayRequestedPower(String s, double power){
    SmartDashboard.putString(s + " Power: ", power + "");
  }

  public boolean isElbowLimitSwitchTriggered(){
    return !limitSwitchDebouncer.calculate(limitSwitch.get());
  }

  public void displayLimitSwitchStatus(){
    SmartDashboard.putString("Limit Switch", isElbowLimitSwitchTriggered() + "");
  }
  public void displayEncoderTarget(){
    SmartDashboard.putString("encoder target", elbowFoldLimit + "");
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}