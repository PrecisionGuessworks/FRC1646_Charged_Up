package frc.robot.Subsystems.Arm.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Arm.ArmSubsystem;
import frc.robot.constants.Constants;
import frc.robot.constants.Constants.ArmConstants.EblowMovement;

public class MoveElbowState extends CommandBase {
    private ArmSubsystem arm = ArmSubsystem.getInstance();
    private EblowMovement desireMotion;

    public MoveElbowState(EblowMovement desireMotion) {
        addRequirements(arm);
        this.desireMotion = desireMotion;
    }

    @Override
    public void initialize() {
        if (desireMotion == EblowMovement.RAISE){
            arm.setElbowPower(-1 * Constants.ArmConstants.ELBOW_SPEED);
        } else if (desireMotion == EblowMovement.LOWER) {
            arm.setElbowPower(Constants.ArmConstants.ELBOW_SPEED);
        } else {
            arm.setElbowPower(0);
        }
        
    }
}
