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

    public void setRedLED(){
        blinkin.set(0.61);
    }
    public void setWhiteLED(){
        blinkin.set(0.93);
    }
    public void setPurpleLED(){
        blinkin.set(0.91);
    }
    public void setYellowLED(){
        blinkin.set(0.69);
    }
    public void setBlueLED(){
        blinkin.set(0.87);
    }


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
