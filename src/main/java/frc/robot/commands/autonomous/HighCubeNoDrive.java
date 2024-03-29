// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Subsystems.Arm.states.MoveElbowState;
import frc.robot.Subsystems.Arm.states.MoveShoulderToPotTarget;
import frc.robot.Subsystems.Arm.states.StopShoulderState;
import frc.robot.Subsystems.Intake.states.IntakingState;
import frc.robot.Subsystems.Intake.states.StopIntakeState;
import frc.robot.Subsystems.Wrist.states.MoveWristState;
import frc.robot.constants.Constants.ArmConstants;
import frc.robot.constants.Constants.ArmConstants.EblowMovement;
import frc.robot.constants.Constants.WristConstants.WristFlexionPosition;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class HighCubeNoDrive extends SequentialCommandGroup {
  /** Creates a new NewScoreAuto. */
  public HighCubeNoDrive() {
    addCommands(
    // Lift Shoulder
    new MoveShoulderToPotTarget(ArmConstants.SHOULDER_HIGH_CUBE_POT_VALUE).withTimeout(1),

    // Lift Elbow
    new MoveElbowState(EblowMovement.RAISE).withTimeout(2),
    new MoveElbowState(EblowMovement.STOP).withTimeout(0.05),

    // Articulate wrist
    new MoveWristState(WristFlexionPosition.LOWER).withTimeout(0.3),
    new MoveWristState(WristFlexionPosition.STOP).withTimeout(0.5),

    // Spit out
    new IntakingState().withTimeout(1.0),
    new StopIntakeState().withTimeout(0.05),

    // Reset Wrist
    new MoveWristState(WristFlexionPosition.RAISE).withTimeout(0.375),
    new MoveWristState(WristFlexionPosition.STOP).withTimeout(0.5),

    // Lower Elbow
    new MoveElbowState(EblowMovement.LOWER).withTimeout(1.6),
    new MoveElbowState(EblowMovement.STOP).withTimeout(0.05),

    // Lower shoulder
    new MoveShoulderToPotTarget(ArmConstants.SHOULDER_STOWED_POT_VALUE).withTimeout(1)

    );
  }
}
