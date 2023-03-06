package frc.robot.Subsystems.Intake.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Intake.IntakeSubsystem;
import frc.robot.constants.Constants.IntakeConstants;

public class IntakingState extends CommandBase {
  public IntakeSubsystem intake = IntakeSubsystem.getInstance();

  public IntakingState() {
    addRequirements(intake);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    intake.setPower(IntakeConstants.INTAKING_SPEED);
  }

}
