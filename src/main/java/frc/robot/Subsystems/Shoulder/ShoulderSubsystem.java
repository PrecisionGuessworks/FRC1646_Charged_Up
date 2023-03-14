// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Shoulder;

import javax.print.attribute.standard.Media;

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

public class ShoulderSubsystem extends SubsystemBase {

  private static ShoulderSubsystem instance;
  private TalonFX shoulderLeftMotor, shoulderRightMotor;
  private AnalogPotentiometer shoulderPot;
  private SlewRateLimiter shoulderFilter;
  
  private MedianFilter potFilter, encoderFilter;
  private Debouncer limitSwitchDebouncer;

  private double shoulderHoldPosition;
  private boolean haveShoulderPosition;

  private ShoulderSubsystem() {
    //Stage 0 to 1
    shoulderLeftMotor = TalonFXFactory.makeTalonFX(RobotMap.SHOULDER_LEFT_ID); 
    shoulderRightMotor = TalonFXFactory.makeTalonFX(RobotMap.SHOULDER_RIGHT_ID); 

    shoulderLeftMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    shoulderRightMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);

    shoulderLeftMotor.setInverted(TalonFXInvertType.CounterClockwise);
    shoulderRightMotor.setInverted(TalonFXInvertType.Clockwise);

    shoulderLeftMotor.setNeutralMode(NeutralMode.Brake);
    shoulderRightMotor.setNeutralMode(NeutralMode.Brake);

    // Extra Sensors
    shoulderPot = new AnalogPotentiometer(RobotMap.SHOULDER_POT_LEFT_ID,100, ArmConstants.SHOULDER_POT_OFFSET);

    // Value Normalizing
    shoulderFilter = new SlewRateLimiter(ArmConstants.SHOULDER_SLEW_RATE_LIMIT);
    encoderFilter = new MedianFilter(50);

    potFilter = new MedianFilter(5); // value is the sample size to take

    shoulderHoldPosition = getShoulderEncoder();
  }

  public static ShoulderSubsystem getInstance(){
    if (instance == null){
      instance = new ShoulderSubsystem();
    }
    return instance;
  }

  public void stopShoulder(){
    shoulderLeftMotor.set(ControlMode.PercentOutput, 0);
    shoulderRightMotor.set(ControlMode.PercentOutput, 0);
  }

  public void setShoulderPower(double power){
    power = shoulderFilter.calculate(power);
    shoulderLeftMotor.set(ControlMode.PercentOutput, power);
    shoulderRightMotor.set(ControlMode.PercentOutput, power);
  }

  private boolean isShoulderTooHigh(double power){
    return power > 0.0 && getShoulderPosition() > ArmConstants.SHOULDER_HIGH_LIMIT;
  }
  private boolean isShoulderTooLow(double power){
    return power < 0.0 && getShoulderEncoder() < ArmConstants.SHOULDER_ENCODER_BOTTOM;
    //return power < 0.0 && getShoulderPosition() < ArmConstants.SHOULDER_LOW_LIMIT;
  }

  public void setShoulderPowerWithSafeties(double power){
    if (isShoulderTooHigh(power)) { 
      setShoulderPower(0);
    } else if (isShoulderTooLow(power)) {
      //setShoulderPower(0);
      holdShoulder();
    } else if (Math.abs(power) < 0.1) {
      if (haveShoulderPosition) {
        holdShoulder();
      } else {
        shoulderHoldPosition = getShoulderEncoder();
        haveShoulderPosition = true;
      }
    } else {
      setShoulderPower(power);
      haveShoulderPosition = false;
    }
  }
  

  public void holdShoulder(){
    setShoulderPositionByEncoder(shoulderHoldPosition);
  }

  public boolean isAtTarget(double target){
    return Math.abs(target - getShoulderPosition()) < ArmConstants.SHOULDER_POT_TOLERANCE;
  }

  public void setShoulderPositionByEncoder(double position){
    shoulderLeftMotor.set(ControlMode.Position, position);
    shoulderRightMotor.set(ControlMode.Position, position);
  }

  public double getShoulderPosition(){
    return potFilter.calculate(shoulderPot.get());
  }

  public void displayArmPositions(){
    SmartDashboard.putString("Shoulder Pot", getShoulderPosition() + "");
  }

  public double getShoulderEncoder(){
    return encoderFilter.calculate(shoulderLeftMotor.getSelectedSensorPosition());
  }

  public void displayRequestedPower(String s, double power){
    SmartDashboard.putString(s + " Power: ", power + "");
  }

  public void displayEncoderTarget(){
    SmartDashboard.putString("Shoulder Encoder Left", getShoulderEncoder() + "");
    SmartDashboard.putString("Shoulder Encoder Right", shoulderRightMotor.getSelectedSensorPosition() + "");
    SmartDashboard.putString("Hold Shoulder Position", shoulderHoldPosition + "");
  }


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}