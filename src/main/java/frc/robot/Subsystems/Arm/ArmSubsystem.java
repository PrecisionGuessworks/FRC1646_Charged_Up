// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.TalonSRXFeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import frc.robot.constants.RobotMap;
import frc.robot.constants.Constants.ArmConstants;
import frc.robot.lib.TalonFXFactory;

public class ArmSubsystem extends SubsystemBase {

  private static ArmSubsystem instance;
  private TalonFX armStage0LeftMotor, armStage0RightMotor, armStage1LeftMotor, armStage1RightMotor;
  
  private ArmSubsystem() {

    //Stage 0 to 1
    armStage0LeftMotor = TalonFXFactory.makeTalonFX(RobotMap.ARM_STAGE0_LEFT_ID); 
    armStage0RightMotor = TalonFXFactory.makeTalonFX(RobotMap.ARM_STAGE0_RIGHT_ID); 

    // Stage 1 to 2
    armStage1LeftMotor = TalonFXFactory.makeTalonFX(RobotMap.ARM_STAGE1_LEFT_ID); 
    armStage1RightMotor = TalonFXFactory.makeTalonFX(RobotMap.ARM_STAGE1_RIGHT_ID);

    armStage0LeftMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    armStage0RightMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);

    armStage1LeftMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    armStage1RightMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);

    armStage0LeftMotor.setInverted(TalonFXInvertType.CounterClockwise);
    armStage0RightMotor.setInverted(TalonFXInvertType.Clockwise);
    armStage1LeftMotor.setInverted(TalonFXInvertType.CounterClockwise);
    armStage1RightMotor.setInverted(TalonFXInvertType.Clockwise);


    armStage0LeftMotor.setNeutralMode(NeutralMode.Brake);
    armStage0RightMotor.setNeutralMode(NeutralMode.Brake);
    armStage1LeftMotor.setNeutralMode(NeutralMode.Brake);
    armStage1RightMotor.setNeutralMode(NeutralMode.Brake);
  }

  public static ArmSubsystem getInstance(){
    if (instance == null){
      instance = new ArmSubsystem();
    }
    return instance;
  }

  public void setShoulderPower(double power){
    // if (power > 0.0 && getShoulderPosition() > Constants.ArmConstants.SHOULDER_HIGH_LIMIT){
    //   armStage0LeftMotor.set(ControlMode.PercentOutput, 0.0);
    //   armStage0RightMotor.set(ControlMode.PercentOutput, 0.0);
    // }else if(power < 0.0 && getShoulderPosition() < Constants.ArmConstants.SHOULDER_LOW_LIMIT){
    //   armStage0RightMotor.set(ControlMode.PercentOutput, ArmConstants.SHOULDER_HOLD_POWER);
    //   armStage0LeftMotor.set(ControlMode.PercentOutput, ArmConstants.SHOULDER_HOLD_POWER);
    // }else{
    //   armStage0LeftMotor.set(ControlMode.PercentOutput, power);
    //   armStage0RightMotor.set(ControlMode.PercentOutput, power);
    // } 

    armStage0LeftMotor.set(ControlMode.PercentOutput, power);
    armStage0RightMotor.set(ControlMode.PercentOutput, power);
  }

  public void setShoulderPosition(double position){
    armStage0LeftMotor.set(ControlMode.Position, position);
    armStage0RightMotor.set(ControlMode.Position, position);
  }

  public double getShoulderPosition(){
    return armStage0LeftMotor.getSelectedSensorPosition();
  }


  public void setElbowPower(double power){
    armStage1LeftMotor.set(ControlMode.PercentOutput, power);
    armStage1RightMotor.set(ControlMode.PercentOutput, power);
  }

  private boolean isElbowTooHigh(double power){
    return power > 0.0 && getElbowPosition() > Constants.ArmConstants.ELBOW_HIGH_LIMIT;
  }
  private boolean isElbowTooLow(double power){
    return power < 0.0 && getElbowPosition() < Constants.ArmConstants.ELBOW_LOW_LIMIT;
  }

  public void setElbowPowerWithSafeties(double power){
    if (isElbowTooHigh(power)) {
      setElbowPower(0);
    } else if (isElbowTooLow(power)) {
      setElbowPower(Constants.ArmConstants.ELBOW_HOLD_POWER);
      // This keeps the arm from sinking
    } else {
      setElbowPower(power);
    }
  }

  public void setElbowPosition(double position) {
    armStage1LeftMotor.set(ControlMode.Position, position);
    armStage1RightMotor.set(ControlMode.Position, position);
  }

  public double getElbowPosition() {
    return armStage1LeftMotor.getSelectedSensorPosition();
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

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}