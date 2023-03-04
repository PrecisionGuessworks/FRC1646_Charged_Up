package frc.robot.Subsystems.Wrist.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Wrist.WristSubsystem;
import frc.robot.constants.Constants;
import frc.robot.lib.Controllers;

public class ManualWristState extends CommandBase{
    public WristSubsystem wrist = WristSubsystem.getInstance();

    public ManualWristState(){
        addRequirements(wrist);
    }

    @Override
    public void execute() {
        double powers[] = new double[2];
        powers[0] = Controllers.getOperatorController().getRawAxis(Controllers.PS4_Controller.Axis.RIGHT_STICK_X);
        powers[1] = Controllers.getOperatorController().getRawAxis(Controllers.PS4_Controller.Axis.RIGHT_STICK_Y);

        wrist.setSupinationPowerWithSafeties(powers[0] * Constants.WristConstants.SUPINATION_SCALAR);
        wrist.setFlexionPowerWithSafeties(powers[1] * Constants.WristConstants.FLEXION_SCALAR);

        wrist.displayWristPositions();
    }
}
