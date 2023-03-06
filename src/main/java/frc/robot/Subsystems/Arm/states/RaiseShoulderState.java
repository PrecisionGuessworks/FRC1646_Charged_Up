package frc.robot.Subsystems.Arm.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Arm.ArmSubsystem;
import frc.robot.constants.Constants;

public class RaiseShoulderState extends CommandBase {
    private ArmSubsystem arm = ArmSubsystem.getInstance();

    public RaiseShoulderState() {
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.setShoulderPowerWithSafeties(Constants.ArmConstants.SHOULDER_SPEED);
    }
}
