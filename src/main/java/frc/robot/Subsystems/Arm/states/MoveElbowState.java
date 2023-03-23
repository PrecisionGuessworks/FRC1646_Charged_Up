package frc.robot.Subsystems.Arm.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Arm.ArmSubsystem;
import frc.robot.Subsystems.Elbow.ElbowSubsystem;
import frc.robot.constants.Constants;
import frc.robot.constants.Constants.ArmConstants.EblowMovement;

public class MoveElbowState extends CommandBase {
    private ElbowSubsystem elbow = ElbowSubsystem.getInstance();
    private EblowMovement desireMotion;

    public MoveElbowState(EblowMovement desireMotion) {
        addRequirements(elbow);
        this.desireMotion = desireMotion;
    }

    @Override
    public void initialize() {
        if (desireMotion == EblowMovement.RAISE){
            elbow.setElbowPowerWithSafeties(Constants.ArmConstants.ELBOW_SPEED);
        } else if (desireMotion == EblowMovement.LOWER) {
            elbow.setElbowPowerWithSafeties(-1 * Constants.ArmConstants.ELBOW_SPEED);
        } else {
            elbow.stopElbow();
        }
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {
        elbow.stopElbow();
    }
}
