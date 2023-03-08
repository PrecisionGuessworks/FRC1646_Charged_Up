package frc.robot.Subsystems.Arm.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Arm.ArmSubsystem;
import frc.robot.constants.Constants.ArmConstants;

public class RaiseElbowState extends CommandBase {
    private ArmSubsystem arm = ArmSubsystem.getInstance();

    public RaiseElbowState() {
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.setElbowPowerWithSafeties(ArmConstants.ELBOW_SPEED);
    }
}