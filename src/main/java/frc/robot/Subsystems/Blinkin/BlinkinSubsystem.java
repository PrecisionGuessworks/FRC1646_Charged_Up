package frc.robot.Subsystems.Blinkin;

import java.awt.Color;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.RobotMap;

public class BlinkinSubsystem extends SubsystemBase{
    private static BlinkinSubsystem instance;
    private Spark blinkin;
    private Colors color;    

    public static BlinkinSubsystem getInstance(){
        if (instance == null){
          instance = new BlinkinSubsystem();
        }
        return instance;
      }

    public BlinkinSubsystem(){
        blinkin = new Spark(RobotMap.BLINKIN_ID);
    }

    public void setColor(Colors chosenColor){
        blinkin.set(chosenColor.getColorVal());
    }

    public void setBlinkinToAllianceColor() {
        boolean isRed = NetworkTableInstance.getDefault().getTable("FMSInfo").getEntry("IsRedAlliance").getBoolean(true);
        if (isRed == true){
            setColor(Colors.RED);
        } else {
            setColor(Colors.BLUE);
        }
    }




    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
