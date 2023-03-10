// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Arm.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Arm.ArmSubsystem;
import frc.robot.Subsystems.Elbow.ElbowSubsystem;
import frc.robot.Subsystems.Shoulder.ShoulderSubsystem;
import frc.robot.constants.Constants;
import frc.robot.constants.Constants.ArmConstants;
import frc.robot.lib.Controllers;

public class ManualArmState extends CommandBase {
  public ElbowSubsystem elbow = ElbowSubsystem.getInstance();
  public ShoulderSubsystem shoulder = ShoulderSubsystem.getInstance();
  
  //public ArmSubsystem arm = ArmSubsystem.getInstance();

  public ManualArmState() {
    addRequirements(elbow);
    addRequirements(shoulder);
  }

  @Override
  public void execute() {
    double shoulderPower = Controllers.getOperatorController().getRawAxis(Controllers.PS4_Controller.Axis.LEFT_STICK_Y);
    double elbowPower = Controllers.getOperatorController().getRawAxis(Controllers.PS4_Controller.Axis.LEFT_STICK_X);

    if (Controllers.getOperatorController().getRawButtonPressed(Controllers.PS4_Controller.Button.OPTIONS)){
      new MoveShoulderToPotTarget(ArmConstants.SHOULDER_MID_CUBE_POT_VALUE);
    } else {
      
    }
    // Shoulder + Elbow Values
    shoulderPower = Constants.ArmConstants.SHOULDER_ROTATION_SCALAR * shoulderPower;
    elbowPower = Constants.ArmConstants.ELBOW_ROTATION_SCALAR * elbowPower;

    // Shoulder Control
    shoulder.setShoulderPowerWithSafeties(shoulderPower);

    // Elbow Control
    elbow.setElbowPowerWithSafeties(elbowPower);
    
    // Send data to SmartDashboard for viewing
    shoulder.displayArmPositions();
    shoulder.displayRequestedPower("Shoulder", shoulderPower);
    elbow.displayRequestedPower("Elbow", elbowPower);
    elbow.displayLimitSwitchStatus();
    elbow.displayEncoderTarget();

    elbow.resetFoldLimit();
  }

}