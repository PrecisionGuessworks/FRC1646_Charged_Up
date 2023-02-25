package frc.robot.constants;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants {
   public static final class DriveConstants{
    public static final double THROTTLE_SCALER = 0.5;
    public static final double ROTATION_SCALE = 0.3;

    public static final double TS = SmartDashboard.getNumber("Throttle Scalar", 0.8);

    public static final double POSITIVE_SLEW_RATE_LIMIT = 0.95;
    public static final double NEGATIVE_SLEW_RATE_LIMIT = -1.75;
   } 

   public static final class ArmConstants{
      // Shoulder
      public static final double SHOULDER_HIGH_LIMIT = 43.5;
      public static final double SHOULDER_LOW_LIMIT = 3.5;
      public static final double SHOULDER_HOLD_POWER = -0.13;
      public static final double SHOULDER_RAISE_SPEED = 0.75;
      public static final double SHOULDER_LOWER_SPEED = -0.75;

      public static final double SHOULDER_ROTATION_SCALAR = -0.5;

      public static final double SHOULDER_SLEW_RATE_LIMIT = 0.75;
      public static final int SHOULDER_POT_OFFSET = -25;

      // Elbow
      public static final double ELBOW_HIGH_LIMIT = 20000.0;
      public static final double ELBOW_LOW_LIMIT = -10000.0;

      public static final double ELBOW_ROTATION_SCALAR = -0.5;

      public static final double ELBOW_HOLD_POWER = -0.13;
      public static final double ELBOW_SLEW_RATE_LIMIT = 0.75;
      public static final double ELBOW_RAISE_SPEED = 0.75;

      

      public static enum ArmPosition{
          STOWED, HIGH_NODE, MED_NODE, MED_CUBE, HIGH_CUBE, GROUND_SCORE, FEEDER_STATION
          // WHAT ARE THESE SCORING LOCATIONS CALLED???
      }
   }

   public static final class WristConstants{
      public static final double LEFT_SUPINATION_LIMIT = -64000;
      public static final double RIGHT_SUPINATION_LIMIT = 64000;
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

      public static final int NEO550_CURRENT_LIMIT = 25;
   }
}
