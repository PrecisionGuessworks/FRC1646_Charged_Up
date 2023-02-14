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


import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import frc.robot.constants.RobotMap;
import frc.robot.constants.Constants.ArmConstants;
import frc.robot.lib.TalonFXFactory;

public class ArmSubsystem extends SubsystemBase {

  private static ArmSubsystem instance;
  private TalonFX armStage0LeftMotor, armStage0RightMotor;
  
  private ArmSubsystem() {
    armStage0LeftMotor = TalonFXFactory.makeTalonFX(RobotMap.ARM_STAGE0_LEFT_ID); 
    armStage0RightMotor = TalonFXFactory.makeTalonFX(RobotMap.ARM_STAGE0_RIGHT_ID); 

    armStage0LeftMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);
    armStage0RightMotor.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, 30);

    armStage0LeftMotor.setInverted(TalonFXInvertType.CounterClockwise);
    armStage0RightMotor.setInverted(TalonFXInvertType.Clockwise);
    armStage0LeftMotor.setNeutralMode(NeutralMode.Brake);
    armStage0RightMotor.setNeutralMode(NeutralMode.Brake);
  }

  public static ArmSubsystem getInstance(){
    if (instance == null){
      instance = new ArmSubsystem();
    }
    return instance;
  }

  public void setPower(double power){
    if (power > 0.0 && getPosition() > Constants.ArmConstants.UPPER_LIMIT){
        armStage0LeftMotor.set(ControlMode.PercentOutput, 0.0);
        armStage0RightMotor.set(ControlMode.PercentOutput, 0.0);
    }else if(power < 0.0 && getPosition() < Constants.ArmConstants.LOWER_LIMIT){
      // Not set to zero as the arm is slightly bouncing when we drive
      armStage0RightMotor.set(ControlMode.PercentOutput, ArmConstants.HOLD_DOWN_POWER);
      armStage0LeftMotor.set(ControlMode.PercentOutput, ArmConstants.HOLD_DOWN_POWER);
    }else{
        armStage0LeftMotor.set(ControlMode.PercentOutput, power);
        armStage0RightMotor.set(ControlMode.PercentOutput, power);
    }
    
  }

  public void setPosition(double position){
    armStage0LeftMotor.set(ControlMode.Position, position);
    armStage0RightMotor.set(ControlMode.Position, position);
  }

  public double getPosition(){
    return armStage0LeftMotor.getSelectedSensorPosition();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}