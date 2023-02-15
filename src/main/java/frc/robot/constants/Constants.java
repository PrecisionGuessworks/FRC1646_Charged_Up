package frc.robot.constants;

public class Constants {
   public static final class DriveConstants{
    public static final double THROTTLE_SCALER = 0.8;
    public static final double ROTATION_SCALE = 0.4;
   } 

   public static final class ArmConstants{
      public static final double UPPER_LIMIT = 45000.0;//-7846.0;
      public static final double LOWER_LIMIT = 1000.0;//-53587.0;

      public static final double HOLD_DOWN_POWER = -0.13;

      public static enum ArmPosition{
          LOW, HIGH
      }
   }

   public static final class WristConstants{
      public static final double LEFT_LIMIT = 1000;
      public static final double RIGHT_LIMIT = 20000;
      public static final double UPPER_LIMIT = 20000;
      public static final double LOWER_LIMIT = 1000;
   }
}
