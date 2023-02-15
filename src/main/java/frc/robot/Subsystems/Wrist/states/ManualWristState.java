package frc.robot.Subsystems.Wrist.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Wrist.WristSubsystem;
import frc.robot.lib.Controllers;

public class ManualWristState extends CommandBase{
    public WristSubsystem wrist = WristSubsystem.getInstance();

    public ManualWristState(){
        addRequirements(wrist);
    }

    @Override
    public void execute() {
      
    }
}
