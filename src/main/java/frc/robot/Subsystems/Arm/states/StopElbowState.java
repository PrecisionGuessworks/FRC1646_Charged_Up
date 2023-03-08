package frc.robot.Subsystems.Arm.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Arm.ArmSubsystem;
import frc.robot.constants.Constants.ArmConstants;

public class StopElbowState extends CommandBase {
    private ArmSubsystem arm = ArmSubsystem.getInstance();

    public StopElbowState() {
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        arm.setElbowPowerWithSafeties(0);
    }
}