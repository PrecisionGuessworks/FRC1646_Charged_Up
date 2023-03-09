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
    }

    public void execute(){
        sign = (currentPot > potTarget) ? -1 : 1;
        arm.setShoulderPowerWithSafeties(sign * Constants.ArmConstants.SHOULDER_SPEED);
        System.out.println("Shoulder target status: " + arm.isAtTarget(potTarget));
    }

    public boolean isFinished(){
        System.out.println("Shoulder target status: " + arm.isAtTarget(potTarget));
        arm.setShoulderPowerWithSafeties(0);
        return arm.isAtTarget(potTarget);
    }
    public void interrupted(){
        arm.setShoulderPowerWithSafeties(0);
    }
    public void end(){
        arm.setShoulderPowerWithSafeties(0);
        System.out.println("MoveShouderToPotTarget Ended");
    }
}
