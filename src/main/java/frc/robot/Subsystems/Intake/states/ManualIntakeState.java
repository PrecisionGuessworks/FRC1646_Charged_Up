package frc.robot.Subsystems.Intake.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Intake.IntakeSubsystem;
import frc.robot.constants.Constants.IntakeConstants;
import frc.robot.lib.Controllers;

public class ManualIntakeState extends CommandBase {
    public IntakeSubsystem intake = IntakeSubsystem.getInstance();

    public ManualIntakeState(){
        addRequirements(intake);
    }

    @Override
    public void execute() {
        // TODO: Change these buttons

        if (Controllers.getOperatorController().getRawButtonPressed(Controllers.PS4_Controller.Button.TRIANGLE)) {
            intake.setPower(IntakeConstants.INTAKING_SPEED);
        } else if (Controllers.getOperatorController().getRawButtonPressed(Controllers.PS4_Controller.Button.X)) {
            intake.setPower(IntakeConstants.OUTTAKING_SPEED);
        } else {
            intake.setPower(0);
        }
    }
}
