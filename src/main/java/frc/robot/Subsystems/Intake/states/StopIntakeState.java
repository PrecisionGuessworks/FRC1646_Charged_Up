// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Intake.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Intake.IntakeSubsystem;
import frc.robot.Subsystems.Wrist.WristSubsystem;

public class StopIntakeState extends CommandBase {
  /** Creates a new StopIntakeState. */
  private IntakeSubsystem intake = IntakeSubsystem.getInstance();
  public StopIntakeState() {
    addRequirements(intake);
  }
  @Override
  public void initialize() {
      intake.setPower(0);
  }

  @Override
  public boolean isFinished() {
      return true;
  }

  @Override
  public void end(boolean interrupted) {
      intake.setPower(0);
  }
}
