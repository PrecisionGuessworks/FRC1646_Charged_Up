// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Intake.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Intake.IntakeSubsystem;
import frc.robot.constants.Constants.IntakeConstants;

public class OuttakingState extends CommandBase {
  public IntakeSubsystem intake = IntakeSubsystem.getInstance();

  public OuttakingState() {
    addRequirements(intake);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.setPower(-1 * IntakeConstants.OUTTAKING_SPEED);
  }

  @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        intake.setPower(0);
    }

}
