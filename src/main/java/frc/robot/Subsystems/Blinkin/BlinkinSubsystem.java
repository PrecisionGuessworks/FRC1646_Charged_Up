package frc.robot.Subsystems.Blinkin;

import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class BlinkinSubsystem extends SubsystemBase{
    private static BlinkinSubsystem instance;
    private Spark blinkin;

    public static BlinkinSubsystem getInstance(){
        if (instance == null){
          instance = new BlinkinSubsystem();
        }
        return instance;
      }

    public BlinkinSubsystem(){
        blinkin = new Spark(9);
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


    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }
}
