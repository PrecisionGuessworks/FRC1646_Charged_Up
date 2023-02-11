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

  public OpenLoopState() {
    addRequirements(drive);
  }

  @Override
  public void execute() {
    double throttle = Controllers.getDriverController().getRawAxis(Controllers.PS4_Controller.Axis.LEFT_STICK_Y);
    double rotation = -1.0 * Controllers.getDriverController().getRawAxis(Controllers.PS4_Controller.Axis.RIGHT_STICK_X);
    drive.curvatureDrive(throttle * DriveConstants.THROTTLE_SCALER, rotation * DriveConstants.ROTATION_SCALE);
  }
}