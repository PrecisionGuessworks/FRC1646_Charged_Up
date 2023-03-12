// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.Subsystems.Wrist.states;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Subsystems.Wrist.WristSubsystem;
import frc.robot.constants.Constants;
import frc.robot.constants.Constants.WristConstants.WristFlexionPosition;

public class MoveWristState extends CommandBase {
  private WristSubsystem wrist = WristSubsystem.getInstance();
  private WristFlexionPosition desireMotion;

  /** Creates a new MoveWristState. */
  public MoveWristState(WristFlexionPosition desireMotion) {
    addRequirements(wrist);
    this.desireMotion = desireMotion;

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if (desireMotion == WristFlexionPosition.RAISE){
      wrist.setFlexionPower(-1* Constants.WristConstants.FLEXION_SPEED);
    } else if (desireMotion == WristFlexionPosition.LOWER) {
      wrist.setFlexionPower(Constants.WristConstants.FLEXION_SPEED);
    } else {
      wrist.stopFlexion();
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    wrist.stopFlexion();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
