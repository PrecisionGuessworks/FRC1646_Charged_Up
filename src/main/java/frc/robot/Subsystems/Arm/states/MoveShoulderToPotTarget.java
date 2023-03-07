package frc.robot.Subsystems.Arm.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Arm.ArmSubsystem;
import frc.robot.constants.Constants;
import frc.robot.constants.Constants.ArmConstants;

public class MoveShoulderToPotTarget extends CommandBase {
    private ArmSubsystem arm = ArmSubsystem.getInstance();
    double potTarget, currentPot;
    int sign;

    public MoveShoulderToPotTarget(double potTarget) {
        this.potTarget = potTarget;
        addRequirements(arm);
    }

    @Override
    public void initialize() {
        while(Math.abs(arm.getShoulderPosition() - potTarget) > ArmConstants.SHOULDER_POT_TOLERANCE) {
            sign = (currentPot > potTarget) ? -1 : 1;
            arm.setShoulderPowerWithSafeties(sign * Constants.ArmConstants.SHOULDER_SPEED);
        }
    }
}
