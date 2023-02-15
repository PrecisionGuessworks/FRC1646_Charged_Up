package frc.robot.lib;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;

public class Controllers {

    private static Joystick driverController;
    private static Joystick operatorController;
    private static PS4Controller driverControllerNew;

    private static final int DRIVE_CONTROLLER_ID = 0;
    private static final int OPERATOR_CONTROLLER_ID = 1;

    public static Joystick getDriverController(){
        if (driverController == null){
            driverController = new Joystick(DRIVE_CONTROLLER_ID);
        }
        return driverController;
    }
    
    public static Joystick getOperatorController(){
        if (operatorController == null){
            operatorController = new Joystick(OPERATOR_CONTROLLER_ID);
        }
        return operatorController;
    }

    public static class PS4_Controller{
        public static class Axis{
            public static final int LEFT_STICK_X = 0;
            public static final int LEFT_STICK_Y = 1;
            public static final int LEFT_TRIGGER = 2;
            public static final int RIGHT_TRIGGER = 3;
            public static final int RIGHT_STICK_X = 4;
            public static final int RIGHT_STICK_Y = 5;
        }

        public static class Button{
            public static final int SQUARE = 3;
            public static final int X = 1;
            public static final int CIRCLE = 2;
            public static final int TRIANGLE = 4;
            public static final int L1_Bumper = 5;
            public static final int R1_Bumper = 6;
            public static final int SHARE = 7;
            public static final int OPTIONS = 8;
            public static final int L_Joy_Button = 9;
            public static final int R_Joy_Button = 10;
        }
    }
    
    public static class XBox_Controller{
        public static class Axis{
            public static final int LEFT_STICK_X = 0;
	        public static final int LEFT_STICK_Y = 1;
	        public static final int LEFT_TRIGGER = 2;
	        public static final int RIGHT_TRIGGER = 3;
	        public static final int RIGHT_STICK_X = 4;
	        public static final int RIGHT_STICK_Y = 5;
        }

        public static class Button{
            public static final int A = 1;
	        public static final int B = 2;
	        public static final int X = 3;
	        public static final int Y = 4;
	        public static final int LB = 5;
	        public static final int RB = 6;
	        public static final int LOGO_LEFT = 7;
	        public static final int LOGO_RIGHT = 8;
	        public static final int LEFT_STICK_BUTTON = 9;
	        public static final int RIGHT_STICK_BUTTON = 10;
        }
    }
}