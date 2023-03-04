package frc.robot.Subsystems.Blinkin;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.lib.Controllers;

public class BlinkinState extends CommandBase{
    private BlinkinSubsystem blinkin = BlinkinSubsystem.getInstance();

    public BlinkinState(){
        addRequirements(blinkin);
    }

    @Override
    public void execute() {
        if (Controllers.getOperatorController().getRawButton(Controllers.PS4_Controller.Button.TRIANGLE)) {
            blinkin.setColor(Colors.YELLOW);
        } else if (Controllers.getOperatorController().getRawButton(Controllers.PS4_Controller.Button.SQUARE)) {
            blinkin.setColor(Colors.PURPLE);
        } else if (Controllers.getOperatorController().getRawButton(Controllers.PS4_Controller.Button.CIRCLE)){
            blinkin.setColor(Colors.RED);
        } else if (Controllers.getDriverController().getRawButton(Controllers.PS4_Controller.Button.CIRCLE)){
            blinkin.setColor(Colors.BLUE);
        } else if (Controllers.getDriverController().getRawButton(Controllers.PS4_Controller.Button.SQUARE)){
            blinkin.setColor(Colors.RAINBOW_COLOR_WAVES);
        } else {
            blinkin.setWhiteLED();
        }
    }
}
