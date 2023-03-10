// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.constants.Constants.DriveConstants;
import frc.robot.lib.Controllers;
import frc.robot.Subsystems.Drivetrain.DrivetrainSubsystem;

public class OpenLoopState extends CommandBase {

  DrivetrainSubsystem drive = DrivetrainSubsystem.getInstance();
  double throttle, rotation;
  boolean halfSpeedRequested = false;

  public OpenLoopState() {
    // this.throttle = throttle;
    // this.rotation = rotation;
    addRequirements(drive);
  }

  @Override
  public void execute() {
    throttle = Controllers.getDriverController().getRawAxis(Controllers.PS4_Controller.Axis.LEFT_STICK_Y);
    rotation = Controllers.getDriverController().getRawAxis(Controllers.PS4_Controller.Axis.RIGHT_STICK_X);

    if(Controllers.getDriverController().getRawButton(Controllers.PS4_Controller.Button.R1_Bumper)){
      halfSpeedRequested = true;
    } else {
      halfSpeedRequested = false;
    }
    
    if(halfSpeedRequested) {
      throttle *= 0.5;
    }

    drive.printDriveEncoders();
    drive.curvatureDrive(throttle * DriveConstants.THROTTLE_SCALER, rotation * DriveConstants.ROTATION_SCALE);
  }
  
}