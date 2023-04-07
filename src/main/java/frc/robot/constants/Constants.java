package frc.robot.constants;

import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants {
   public static final class DriveConstants{
    public static final double THROTTLE_SCALER = 0.8;
    public static final double ROTATION_SCALAR = 0.2;

    public static final double TS = SmartDashboard.getNumber("Throttle Scalar", 0.8);

    public static final double POSITIVE_SLEW_RATE_LIMIT = 0.95;
    public static final double NEGATIVE_SLEW_RATE_LIMIT = -1.75;
   } 

   public static final class ArmConstants{
      // Shoulder
      public static final double SHOULDER_HIGH_LIMIT = 26; // 43.5
      public static final double SHOULDER_LOW_LIMIT = -9; // old: 6.8
      public static final double SHOULDER_HOLD_POWER = -0.13;
      public static final double SHOULDER_SPEED = 0.5;  // 0.36

      public static final double SHOULDER_ROTATION_SCALAR = -0.8;

      public static final double SHOULDER_SLEW_RATE_LIMIT = 0.75;
      public static final int SHOULDER_POT_OFFSET = -25;

      public static final double SHOULDER_HIGH_CUBE_POT_VALUE = 3;
      public static final double SHOULDER_MID_CUBE_POT_VALUE = 2;
      public static final double SHOULDER_STOWED_POT_VALUE = -8;
      public static final double SHOULDER_POT_TOLERANCE = .5;
      public static final double SHOULDER_ENCODER_BOTTOM = 18000;

      public static final class ShoulderTarget{
         public static final double STOWED = -9;
         public static final double FEEDER = -5.15;
         public static final double MED_CUBE = -2.5;
         public static final double HIGH_CUBE = 4.5;
         public static final double MED_CONE = 6;
         public static final double HIGH_CONE = 8;
      }

      // Elbow
      public static final double ELBOW_HIGH_LIMIT = 20000.0;
      public static final double ELBOW_LOW_LIMIT = -10000.0;

      public static final double ELBOW_ROTATION_SCALAR = -0.7;

      public static final double ELBOW_HOLD_POWER = -0.13;
      public static final double ELBOW_SLEW_RATE_LIMIT = 1.1;
      public static final double ELBOW_SPEED = -0.25; // -0.25
      public static final double ELBOW_SPEED_QUICK = -0.5;

      public static final double ELBOW_TRAVEL_DELTA = 10000;
      public static final double ELBOW_LIMIT_SWITCH_DEBOUNCE_TIME = 0.1;

      // Stage 1 Config
      public static final double STAGE1_LENGTH = 0.8382; // UNITS: meters
      public static final double STAGE1_MOI = 0.4;
      public static final double STAGE1_CGRADIUS = 1.0;
      public static final double STAGE1_MASS = 2.0;
      public static final DCMotor STAGE1_MOTOR = DCMotor.getFalcon500(2).withReduction(355);

      // Stage 2 Config
      public static final double STAGE2_LENGTH = 0.8382; // UNITS: meters
      public static final double STAGE2_MOI = 0.4;
      public static final double STAGE2_CGRADIUS = 1.0;
      public static final double STAGE2_MASS = 2.0;
      public static final DCMotor STAGE2_MOTOR = DCMotor.getFalcon500(2).withReduction(225);

      // Motor neutral deadband : Range 0.001 -> 0.25
      public static final double NEUTRAL_DEADBAND = 0.01;

      public static final int TIMEOUT = 10;

      public static enum EblowMovement{
         LOWER, RAISE, STOP
      }
      public static enum ArmPosition{
          STOWED, HIGH_NODE, MED_NODE, MED_CUBE, HIGH_CUBE, GROUND_SCORE, FEEDER_STATION
          // WHAT ARE THESE SCORING LOCATIONS CALLED???
      }
   }

   public static final class WristConstants{
      public static final double LEFT_SUPINATION_LIMIT = -64000;
      public static final double RIGHT_SUPINATION_LIMIT = 64000;
      public static final double UPPER_FLEXION_LIMIT = -45000;
      public static final double LOWER_FLEXION_LIMIT = 45000;

      public static final double SUPINATION_SCALAR = 0.35;
      public static final double FLEXION_SCALAR = 0.25;
      public static final double FLEXION_SPEED = 0.25;

      public static enum WristFlexionPosition{
         RAISE, LOWER, STOP
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

      public static final int NEO550_CURRENT_LIMIT = 20;
   }
}
