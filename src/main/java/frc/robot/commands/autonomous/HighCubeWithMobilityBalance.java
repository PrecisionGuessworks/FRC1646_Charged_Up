// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Subsystems.Elbow.states.MoveElbowState;
import frc.robot.Subsystems.Shoulder.states.MoveShoulderToPotTarget;
import frc.robot.Subsystems.Drivetrain.states.Balance;
import frc.robot.Subsystems.Drivetrain.states.BalanceFromMidField;
import frc.robot.Subsystems.Drivetrain.states.DriveBackwards;
import frc.robot.Subsystems.Drivetrain.states.DriveBackwardsWithCaution;
import frc.robot.Subsystems.Drivetrain.states.StopDriving;
import frc.robot.Subsystems.Intake.states.IntakingState;
import frc.robot.Subsystems.Intake.states.StopIntakeState;
import frc.robot.Subsystems.Wrist.states.MoveWristState;
import frc.robot.constants.Constants.ArmConstants;
import frc.robot.constants.Constants.ArmConstants.EblowMovement;
import frc.robot.constants.Constants.ArmConstants.ShoulderTarget;
import frc.robot.constants.Constants.WristConstants.WristFlexionPosition;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class HighCubeWithMobilityBalance extends SequentialCommandGroup {
  /** Creates a new HighCubeWithChargingStation. */
  public HighCubeWithMobilityBalance() {
    addCommands(
    // Lift Arm
    new ParallelCommandGroup(
        new MoveShoulderToPotTarget(ArmConstants.SHOULDER_HIGH_CUBE_POT_VALUE).withTimeout(1.25),
        new MoveElbowState(EblowMovement.RAISE, true).withTimeout(0.9),
        new MoveWristState(WristFlexionPosition.LOWER).withTimeout(0.375)
    ),

    // Spit out
    new IntakingState().withTimeout(1.0),

    // Reset Arm
    new ParallelCommandGroup(
        new MoveWristState(WristFlexionPosition.RAISE).withTimeout(0.375),
        new MoveElbowState(EblowMovement.LOWER, true).withTimeout(0.9),
        new MoveShoulderToPotTarget(ArmConstants.SHOULDER_STOWED_POT_VALUE).withTimeout(1.3)
    ),

    // Drive for mobility
    new DriveBackwards().withTimeout(4),
    new DriveBackwardsWithCaution().withTimeout(0.75),
    new StopDriving().withTimeout(0.5),


    // Go for Balance
    new BalanceFromMidField()
    );
  }
}