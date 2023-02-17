package frc.robot.Subsystems.Intake.states;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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

        if (Controllers.getOperatorController().getRawButton(Controllers.PS4_Controller.Button.TRIANGLE)) {
            intake.setPower(IntakeConstants.INTAKING_SPEED);
        } else if (Controllers.getOperatorController().getRawButton(Controllers.PS4_Controller.Button.X)) {
            intake.setPower(IntakeConstants.OUTTAKING_SPEED);
        } else {
            intake.setPower(0);
        }

        SmartDashboard.putString("triangle state: ", Controllers.getOperatorController().getRawButton(Controllers.PS4_Controller.Button.TRIANGLE) + "");


    }
}
