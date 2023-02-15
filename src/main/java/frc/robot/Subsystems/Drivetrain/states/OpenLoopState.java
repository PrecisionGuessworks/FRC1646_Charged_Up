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

  public OpenLoopState(double throttle, double rotation) {
    this.throttle = throttle;
    this.rotation = rotation;
    addRequirements(drive);
  }

  @Override
  public void execute() {
    drive.curvatureDrive(throttle * DriveConstants.THROTTLE_SCALER, rotation * DriveConstants.ROTATION_SCALE);
  }
  
}