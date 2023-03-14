// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Elbow.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Arm.ArmSubsystem;
import frc.robot.Subsystems.Elbow.ElbowSubsystem;
import frc.robot.Subsystems.Shoulder.ShoulderSubsystem;
import frc.robot.constants.Constants;
import frc.robot.constants.Constants.ArmConstants;
import frc.robot.lib.Controllers;

public class ManualElbowState extends CommandBase {
  public ElbowSubsystem elbow = ElbowSubsystem.getInstance();
  
  //public ArmSubsystem arm = ArmSubsystem.getInstance();

  public ManualElbowState() {
    addRequirements(elbow);
  }

  @Override
  public void execute() {
    // Elbow Values
    double elbowPower = Controllers.getOperatorController().getRawAxis(Controllers.PS4_Controller.Axis.LEFT_STICK_X);
    elbowPower = Constants.ArmConstants.ELBOW_ROTATION_SCALAR * elbowPower;

    // Elbow Control
    elbow.setElbowPowerWithSafeties(elbowPower);
    
    // Send data to SmartDashboard for viewing
    elbow.displayRequestedPower("Elbow", elbowPower);
    elbow.displayLimitSwitchStatus();
    elbow.displayEncoderTarget();

    elbow.resetFoldLimit();
  }

}