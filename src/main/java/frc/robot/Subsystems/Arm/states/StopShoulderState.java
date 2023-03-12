package frc.robot.Subsystems.Arm.states;

import frc.robot.Subsystems.Arm.ArmSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class StopShoulderState extends CommandBase {
    private ArmSubsystem arm = ArmSubsystem.getInstance();

    public StopShoulderState(){
        addRequirements(arm);
    }
    @Override
    public void initialize() {
        arm.setShoulderPower(0);
        System.out.println("Hello from Stop Shoulder");
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
