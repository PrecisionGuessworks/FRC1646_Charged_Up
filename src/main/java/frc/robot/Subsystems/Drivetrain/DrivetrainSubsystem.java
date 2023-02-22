// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax.IdleMode;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.drive.DifferentialDrive.WheelSpeeds;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.Constants;
import frc.robot.constants.RobotMap;
import frc.robot.lib.PIDConfig;
import frc.robot.lib.TalonFXFactory;

public class DrivetrainSubsystem extends SubsystemBase {
  
  private TalonFX frontRightMotor, topRightMotor, backRightMotor;
  private TalonFX frontLeftMotor, topLeftMotor, backLeftMotor; 

  private SlewRateLimiter throttleFilter, rotationFilter;

  private static DrivetrainSubsystem instance;

  private DrivetrainSubsystem() {
    frontLeftMotor = TalonFXFactory.makeTalonFX(RobotMap.DRIVETRAIN_LEFT_FRONT_ID, TalonFXInvertType.Clockwise, new PIDConfig(0.0, 0.0, 0.0, 0.0));
    topLeftMotor = TalonFXFactory.makeFollowerTalonFX(RobotMap.DRIVETRAIN_LEFT_TOP_ID, frontLeftMotor);
    backLeftMotor = TalonFXFactory.makeFollowerTalonFX(RobotMap.DRIVETRAIN_LEFT_BACK_ID, frontLeftMotor);

    frontRightMotor = TalonFXFactory.makeTalonFX(RobotMap.DRIVETRAIN_RIGHT_FRONT_ID, TalonFXInvertType.CounterClockwise, new PIDConfig(0.0, 0.0, 0.0, 0.0));
    topRightMotor = TalonFXFactory.makeFollowerTalonFX(RobotMap.DRIVETRAIN_RIGHT_TOP_ID, frontRightMotor);
    backRightMotor = TalonFXFactory.makeFollowerTalonFX(RobotMap.DRIVETRAIN_RIGHT_BACK_ID, frontRightMotor);

    setBrakeMode(true);

    throttleFilter = new SlewRateLimiter(Constants.DriveConstants.POSITIVE_SLEW_RATE_LIMIT, Constants.DriveConstants.NEGATIVE_SLEW_RATE_LIMIT, 0);
    rotationFilter = new SlewRateLimiter(0.95);
  }

  public static synchronized DrivetrainSubsystem getInstance(){
    if (instance == null){
      instance = new DrivetrainSubsystem();
    }
    return instance;
  }

  public void setPower(double leftPower, double rightPower){
    frontLeftMotor.set(TalonFXControlMode.PercentOutput, leftPower);
    frontRightMotor.set(TalonFXControlMode.PercentOutput, rightPower);
  }

  public void curvatureDrive(double throttle, double rotation){
    boolean quickTurn = true;
    if (throttle > 0.15){
      quickTurn = false;
    }

    throttle = throttleFilter.calculate(throttle); // TODO: Figure out if this should actually go here or not
    rotation = rotationFilter.calculate(rotation);
    
    //Note: Even though the variable is called wheel speed, this is actually for wheel powers
    WheelSpeeds wheelSpeed = DifferentialDrive.curvatureDriveIK(throttle, rotation, quickTurn);

    setPower(wheelSpeed.left, wheelSpeed.right);
  }

  public void setSpeed(double leftSpeed, double rightSpeed){
    frontLeftMotor.set(TalonFXControlMode.Velocity, leftSpeed);
    frontRightMotor.set(TalonFXControlMode.Velocity, rightSpeed);
  }


  public void setBrakeMode(boolean brake){
    NeutralMode mode = brake ? NeutralMode.Brake : NeutralMode.Coast;
    frontLeftMotor.setNeutralMode(mode);
    frontRightMotor.setNeutralMode(mode);
    backLeftMotor.setNeutralMode(mode);
    backRightMotor.setNeutralMode(mode);
    topLeftMotor.setNeutralMode(mode);
    topRightMotor.setNeutralMode(mode);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}