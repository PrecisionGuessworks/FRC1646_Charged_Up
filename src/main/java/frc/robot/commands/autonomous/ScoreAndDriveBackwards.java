// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Subsystems.Arm.states.RaiseElbowState;
import frc.robot.Subsystems.Arm.states.RaiseShoulderState;
import frc.robot.Subsystems.Drivetrain.states.DriveBackwards;
import frc.robot.Subsystems.Intake.states.OuttakingState;


// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class ScoreAndDriveBackwards extends SequentialCommandGroup {
  /** Creates a new ScoreAndDriveBackwards. */
  public ScoreAndDriveBackwards() {
    // Add your commands in the addCommands() call, e.g.
    // addCommands(new FooCommand(), new BarCommand());
    addCommands(
        // Arm unfold
        new RaiseShoulderState().withTimeout(0.5),
        new RaiseElbowState().withTimeout(0.75),

        // spit out
        new OuttakingState().withTimeout(1.0),

        // refold


        // drive backwards
        new DriveBackwards().withTimeout(1.5)

    );
  }
}