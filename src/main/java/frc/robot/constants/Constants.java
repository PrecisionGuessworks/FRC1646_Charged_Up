package frc.robot.constants;

public class Constants {
   public static final class DriveConstants{
    public static final double THROTTLE_SCALER = 0.65;
    public static final double ROTATION_SCALE = 0.3;

    public static final double SLEW_RATE_LIMIT = 0.5;
   } 

   public static final class ArmConstants{
      // Stage 0-1
      public static final double SHOULDER_HIGH_LIMIT = 45000;
      public static final double SHOULDER_LOW_LIMIT = 45000;
      public static final double SHOULDER_ROTATION_SCALAR = -0.5;

      // Stage 1-2
      public static final double ELBOW_HIGH_LIMIT = 20000.0;
      public static final double ELBOW_LOW_LIMIT = -10000.0;
      public static final double ELBOW_ROTATION_SCALAR = -0.5;

      public static final double SHOULDER_HOLD_POWER = -0.13;
      public static final double ELBOW_HOLD_POWER = -0.13;

      public static enum ArmPosition{
          STOWED, HIGH_NODE, MED_NODE, MED_CUBE, HIGH_CUBE, GROUND_SCORE, FEEDER_STATION
          // WHAT ARE THESE SCORING LOCATIONS CALLED???
      }
   }

   public static final class WristConstants{
      public static final double LEFT_SUPINATION_LIMIT = 1000;
      public static final double RIGHT_SUPINATION_LIMIT = 20000;
      public static final double UPPER_FLEXION_LIMIT = 20000;
      public static final double LOWER_FLEXION_LIMIT = 1000;

      public static final double SUPINATION_SCALAR = 0.25;
      public static final double FLEXION_SCALAR = 0.25;

      public static enum WristFlexionPosition{
         STRAIGHT_OUT, FEEDER
         // idk, maybe
      }
      public static enum WristSupinationPosition{
         NINETY_LEFT, NINETY_RIGHT, LEVEL
         // idk, maybe
      }

   }

   public static final class IntakeConstants {
      public static final double INTAKING_SPEED = 0.8;
      public static final double OUTTAKING_SPEED = -0.8;
   }
}
