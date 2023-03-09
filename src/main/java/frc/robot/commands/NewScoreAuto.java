// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.Subsystems.Arm.states.MoveElbowState;
import frc.robot.Subsystems.Arm.states.MoveShoulderToPotTarget;
import frc.robot.Subsystems.Arm.states.StopShoulderState;
import frc.robot.Subsystems.Intake.states.IntakingState;
import frc.robot.constants.Constants.ArmConstants;
import frc.robot.constants.Constants.ArmConstants.EblowMovement;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class NewScoreAuto extends SequentialCommandGroup {
  /** Creates a new NewScoreAuto. */
  public NewScoreAuto() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
      new MoveShoulderToPotTarget(ArmConstants.SHOULDER_HIGH_CUBE_POT_VALUE),
      new MoveElbowState(EblowMovement.RAISE),
      new WaitCommand(0.5),
      new MoveElbowState(EblowMovement.STOP)
    );
  }
}
