// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Arm.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Arm.ArmSubsystem;
import frc.robot.constants.Constants;
import frc.robot.lib.Controllers;

public class ManualArmState extends CommandBase {
  
  public ArmSubsystem arm = ArmSubsystem.getInstance();

  public ManualArmState() {
    addRequirements(arm);
  }

  @Override
  public void execute() {
    double shoulderPower = Controllers.getOperatorController().getRawAxis(Controllers.PS4_Controller.Axis.LEFT_STICK_Y);
    double elbowPower = Controllers.getOperatorController().getRawAxis(Controllers.PS4_Controller.Axis.LEFT_STICK_X);

    // Shoulder + Elbow Values
    shoulderPower = Constants.ArmConstants.SHOULDER_ROTATION_SCALAR * shoulderPower;
    elbowPower = Constants.ArmConstants.ELBOW_ROTATION_SCALAR * elbowPower;

    // Shoulder Control
    arm.setShoulderPowerWithSafeties(shoulderPower);

    // Elbow Control
    arm.setElbowPower(elbowPower);
    
    // Send data to SmartDashboard for viewing
    arm.displayArmPositions();
    arm.displayShoulderPot();
    arm.displayRequestedPower("Shoulder", shoulderPower);
    arm.displayRequestedPower("Elbow", elbowPower);
  }

}