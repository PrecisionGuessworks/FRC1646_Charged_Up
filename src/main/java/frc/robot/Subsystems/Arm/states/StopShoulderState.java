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
        arm.setShoulderPowerWithSafeties(0);
        System.out.println("Hello from Stop Shoulder");
    }
    public void interrupted(){
        end(true);
    }
    public boolean end(){
        return true;
    }
}
