package frc.robot.Subsystems.Arm.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Arm.ArmSubsystem;
import frc.robot.constants.Constants;

public class LowerElbowState extends CommandBase {
    private ArmSubsystem arm = ArmSubsystem.getInstance();

    public LowerElbowState() {
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.setElbowPowerWithSafeties(-1 * Constants.ArmConstants.ELBOW_SPEED);
    }
}
