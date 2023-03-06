package frc.robot.Subsystems.Arm.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Arm.ArmSubsystem;
import frc.robot.constants.Constants.ArmConstants;

public class RaiseArmState extends CommandBase {
    private ArmSubsystem arm = ArmSubsystem.getInstance();

    public RaiseArmState() {
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.setShoulderPowerWithSafeties(ArmConstants.SHOULDER_SPEED);
        arm.setElbowPowerWithSafeties(ArmConstants.ELBOW_SPEED);
    }
}