package frc.robot.Subsystems.Arm.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Shoulder.ShoulderSubsystem;
import frc.robot.constants.Constants;
import frc.robot.constants.Constants.ArmConstants;

public class MoveShoulderToPotTarget extends CommandBase {
    private ShoulderSubsystem shoulder = ShoulderSubsystem.getInstance();
    double potTarget, currentPot;
    int sign;

    public MoveShoulderToPotTarget(double potTarget) {
        this.potTarget = potTarget;
        addRequirements(shoulder);
    }

    @Override
    public void initialize() {
    }

    public void execute(){
        sign = (currentPot > potTarget) ? -1 : 1;
        shoulder.setShoulderPowerWithSafeties(sign * Constants.ArmConstants.SHOULDER_SPEED);
        //System.out.println("Shoulder target status: " + shoulder.isAtTarget(potTarget));
    }

    public boolean isFinished(){
        //System.out.println("Shoulder target status: " + shoulder.isAtTarget(potTarget));
        return shoulder.isAtTarget(potTarget);
    }

    @Override
    public void end(boolean interrupted){
        shoulder.stopShoulder();
        //System.out.println("MoveShouderToPotTarget Ended");
    }
}
