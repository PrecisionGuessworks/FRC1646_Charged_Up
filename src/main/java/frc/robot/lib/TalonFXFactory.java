package frc.robot.lib;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class TalonFXFactory {
   public static TalonFX makeTalonFX(int id){
      TalonFX talon = new TalonFX(id);
      talon.configFactoryDefault();
      return talon;
   }

   public static TalonFX makeTalonFX(int id, TalonFXInvertType direction){
      TalonFX talon = makeTalonFX(id);
      talon.setInverted(direction);
      return talon;
   }

   public static TalonFX makeTalonFX(int id, TalonFXInvertType direction, PIDConfig pidconfig){
      TalonFX talon = makeTalonFX(id);
      talon.setInverted(direction);
      if(talon.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, 0, 30) != null){
         System.out.println("ConfigSelectedFeedbackSensor failed");
     }
     talon.setSensorPhase(false);

      talon.config_kP(0, pidconfig.kP, 30);
      talon.config_kI(0, pidconfig.kI, 30);
      talon.config_kD(0, pidconfig.kD, 30);
      talon.config_kF(0, pidconfig.kF, 30);
      return talon;
   }

   public static TalonFX makeFollowerTalonFX(int id, TalonFX leader){
      TalonFX talon = makeTalonFX(id);
      talon.set(ControlMode.Follower, leader.getDeviceID());
      talon.setInverted(TalonFXInvertType.FollowMaster);
      return talon;
   }
}