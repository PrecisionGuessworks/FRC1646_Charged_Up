package frc.robot.Subsystems.Elbow.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Elbow.ElbowSubsystem;
import frc.robot.constants.Constants;
import frc.robot.constants.Constants.ArmConstants.EblowMovement;

public class MoveElbowState extends CommandBase {
    private ElbowSubsystem arm = ElbowSubsystem.getInstance();
    private EblowMovement desireMotion;

    public MoveElbowState(EblowMovement desireMotion) {
        addRequirements(arm);
        this.desireMotion = desireMotion;
    }

    @Override
    public void initialize() {
        if (desireMotion == EblowMovement.RAISE){
            arm.setElbowPowerWithSafeties(Constants.ArmConstants.ELBOW_SPEED);
        } else if (desireMotion == EblowMovement.LOWER) {
            arm.setElbowPowerWithSafeties(-1 * Constants.ArmConstants.ELBOW_SPEED);
        } else {
            arm.stopElbow();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        arm.stopElbow();
    }
}
