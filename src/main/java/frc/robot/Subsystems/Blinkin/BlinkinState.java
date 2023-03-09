package frc.robot.Subsystems.Blinkin;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.lib.Controllers;
import frc.robot.lib.Controllers.PS4_Controller;

public class BlinkinState extends CommandBase{
    private BlinkinSubsystem blinkin = BlinkinSubsystem.getInstance();
    private boolean yellowOn, purpleOn, doneSignalling;

    public BlinkinState(){
        addRequirements(blinkin);
        blinkin.setBlinkinToAllianceColor();
        doneSignalling = true;
    }

    @Override
    public void execute() {
        if (Controllers.getOperatorController().getRawButton(PS4_Controller.Button.TRIANGLE)) {
            blinkin.setColor(Colors.YELLOW);
            doneSignalling = false;
        } else if (Controllers.getOperatorController().getRawButton(PS4_Controller.Button.SQUARE)) {
            blinkin.setColor(Colors.PURPLE);
            doneSignalling = false;
        } else if (Controllers.getDriverController().getRawButton(PS4_Controller.Button.SQUARE)){
            blinkin.setColor(Colors.RAINBOW_COLOR_WAVES);
            doneSignalling = false;
        } else if (Controllers.getOperatorController().getRawButton(PS4_Controller.Button.R1_Bumper)) {
            blinkin.setBlinkinToAllianceColor();
            doneSignalling = true;
        } else if (doneSignalling) {
            blinkin.setBlinkinToAllianceColor();
        }
    }
}
