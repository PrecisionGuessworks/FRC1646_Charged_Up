// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Shoulder.states;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Arm.ArmSubsystem;
import frc.robot.Subsystems.Elbow.ElbowSubsystem;
import frc.robot.Subsystems.Shoulder.ShoulderSubsystem;
import frc.robot.constants.Constants;
import frc.robot.constants.Constants.ArmConstants;
import frc.robot.lib.Controllers;

public class ManualShoulderState extends CommandBase {
  public ShoulderSubsystem shoulder = ShoulderSubsystem.getInstance();
  private Joystick operator = Controllers.getOperatorController();
  
  //public ArmSubsystem arm = ArmSubsystem.getInstance();

  public ManualShoulderState() {
    addRequirements(shoulder);
  }

  @Override
  public void execute() {
    // Shoulder Values
    double shoulderPower = operator.getRawAxis(Controllers.PS4_Controller.Axis.LEFT_STICK_Y);
    shoulderPower = Constants.ArmConstants.SHOULDER_ROTATION_SCALAR * shoulderPower;

    if(operator.getRawButton(Controllers.PS4_Controller.Button.X)) {
      shoulder.moveShoulderToPotTarget(ArmConstants.ShoulderTarget.FEEDER);
    } else if (operator.getPOV() == 0) { // Dpad Up
      shoulder.moveShoulderToPotTarget(ArmConstants.ShoulderTarget.HIGH_CUBE);
    } else if (operator.getPOV() == 90) { // Dpad Right
      shoulder.moveShoulderToPotTarget(ArmConstants.ShoulderTarget.HIGH_CONE);
    } else if (operator.getPOV() == 180) { // Dpad Down
      shoulder.moveShoulderToPotTarget(ArmConstants.ShoulderTarget.MED_CUBE);
    } else if (operator.getPOV() == 270) { // Dpad Left
      shoulder.moveShoulderToPotTarget(ArmConstants.ShoulderTarget.MED_CONE);
    } else {
      shoulder.setShoulderPowerWithSafeties(shoulderPower);
    }

    // Arm holding?
    boolean withinDeadband = operator.getRawAxis(Controllers.PS4_Controller.Axis.LEFT_STICK_Y) < 0.1;
    
    
    // Send data to SmartDashboard for viewing
    shoulder.displayArmPositions();
    shoulder.displayRequestedPower("Shoulder", shoulderPower);
  }


}