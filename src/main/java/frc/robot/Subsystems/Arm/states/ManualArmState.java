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

    // Shoulder + Elbow Values
    double shoulderPower = -0.5 * Controllers.getOperatorController().getRawAxis(Controllers.PS4_Controller.Axis.LEFT_STICK_Y);
    double elbowPower = -0.5 * Controllers.getOperatorController().getRawAxis(Controllers.PS4_Controller.Axis.LEFT_STICK_X);

    // Shoulder Control
    // if (shoulderPower > 0.0 && arm.getShoulderPosition() > Constants.ArmConstants.SHOULDER_HIGH_LIMIT) { // Arm too high
    //   arm.setShoulderPower(0);
    // } else if (shoulderPower < 0.0 && arm.getShoulderPosition() < Constants.ArmConstants.SHOULDER_LOW_LIMIT) { // Arm too low
    //   arm.setShoulderPower(0);
    // } else {
    //   arm.setShoulderPower(shoulderPower);
    // }
    arm.setShoulderPower(shoulderPower);

    // Elbow Control
    // if (elbowPower > 0.0 && arm.getElbowPosition() > Constants.ArmConstants.ELBOW_HIGH_LIMIT) {
    //   arm.setElbowPower(0);
    // } else if (elbowPower < 0.0 && arm.getElbowPosition() < Constants.ArmConstants.ELBOW_LOW_LIMIT) {
    //   arm.setElbowPower(0);
    // } else {
    //   arm.setElbowPower(elbowPower);
    // }
    arm.setElbowPower(elbowPower);
    // NOTE: These are worst case value limits. Given the arm geometry and many ways it needs to fold, this will not stop crashes. That'll need math.

  }

}