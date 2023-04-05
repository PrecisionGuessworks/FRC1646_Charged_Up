// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Drivetrain.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Drivetrain.DrivetrainSubsystem;

public class DriveBackwardsWithVigor extends CommandBase {
  DrivetrainSubsystem drive = DrivetrainSubsystem.getInstance();
  public DriveBackwardsWithVigor() {
    addRequirements(drive);
  }

  @Override
  public void initialize() {
    drive.curvatureDrive(0.65, 0, false);
  }

  @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        drive.curvatureDrive(0, 0, false);
    }

}