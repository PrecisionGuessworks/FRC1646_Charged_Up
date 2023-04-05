package frc.robot.Subsystems.Elbow.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Elbow.ElbowSubsystem;
import frc.robot.constants.Constants;
import frc.robot.constants.Constants.ArmConstants.EblowMovement;

public class MoveElbowState extends CommandBase {
    private ElbowSubsystem elbow = ElbowSubsystem.getInstance();
    private EblowMovement desireMotion;
    double elbowSpeed = Constants.ArmConstants.ELBOW_SPEED;

    public MoveElbowState(EblowMovement desireMotion, boolean speedMode) {
        addRequirements(elbow);
        this.desireMotion = desireMotion;
        if (speedMode) {
            elbowSpeed = Constants.ArmConstants.ELBOW_SPEED_QUICK;
        }
    }

    @Override
    public void initialize() {
        if (desireMotion == EblowMovement.RAISE){
            elbow.setElbowPowerWithSafeties(elbowSpeed);
        } else if (desireMotion == EblowMovement.LOWER) {
            elbow.setElbowPowerWithSafeties(-1 * elbowSpeed);
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
