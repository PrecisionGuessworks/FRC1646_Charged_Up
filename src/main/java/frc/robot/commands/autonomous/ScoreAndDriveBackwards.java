// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Subsystems.Arm.states.MoveElbowState;
import frc.robot.Subsystems.Arm.states.RaiseShoulderState;
import frc.robot.Subsystems.Arm.states.StopElbowState;
import frc.robot.Subsystems.Arm.states.MoveShoulderToPotTarget;
import frc.robot.Subsystems.Arm.states.RaiseElbowState;
import frc.robot.Subsystems.Drivetrain.states.DriveBackwards;
import frc.robot.Subsystems.Intake.states.OuttakingState;
import frc.robot.constants.Constants;
import frc.robot.constants.Constants.ArmConstants;
import frc.robot.constants.Constants.ArmConstants.EblowMovement;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ScoreAndDriveBackwards extends SequentialCommandGroup {
  /** Creates a new ScoreAndDriveBackwards. */

  public ScoreAndDriveBackwards() {
    //addSequential(new RaiseShoulderState().withTimeout(0.25));

    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      
      new MoveShoulderToPotTarget(ArmConstants.SHOULDER_HIGH_CUBE_POT_VALUE),
      new MoveElbowState(EblowMovement.RAISE).withTimeout(0.5),
      new MoveElbowState(EblowMovement.STOP),

      // articulate wrist????

      // // spit out
      new OuttakingState().withTimeout(1.0),

      // // refold
      new MoveElbowState(EblowMovement.LOWER).withTimeout(0.5),
      new MoveElbowState(EblowMovement.STOP),

      // // lower shoulder
      new MoveShoulderToPotTarget(ArmConstants.SHOULDER_STOWED_POT_VALUE)

      // drive backwards
      //new DriveBackwards().withTimeout(1.5)


    );
  }
}