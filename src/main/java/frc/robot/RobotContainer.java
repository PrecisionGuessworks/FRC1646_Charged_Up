// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistribution;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.PS4Controller.Button;
import edu.wpi.first.wpilibj.PowerDistribution.ModuleType;

import frc.robot.lib.Controllers;

import frc.robot.Subsystems.Drivetrain.DrivetrainSubsystem;
import frc.robot.Subsystems.Drivetrain.states.OpenLoopState;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Subsystem;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {


  DrivetrainSubsystem drive;

  PowerDistribution powerDistrubutionBoard;


  public RobotContainer() {
    initilizeSubsystems();
    initilizePowerDistrubutionBoard();
    setAllDefaultCommands();
    configureButtonBindings();
  }

  public void initilizeSubsystems(){
    drive = DrivetrainSubsystem.getInstance();

  }

  public void initilizePowerDistrubutionBoard(){
    powerDistrubutionBoard = new PowerDistribution(1, ModuleType.kRev);
    powerDistrubutionBoard.clearStickyFaults();
  }

  public void setAllDefaultCommands(){
    setDefaultCommand(drive, new OpenLoopState());

  }

  public void setDefaultCommand(Subsystem subsystem, Command defaultCommand){
    CommandScheduler.getInstance().setDefaultCommand(subsystem, defaultCommand);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    Joystick drive_joystick = Controllers.getDriverController();
    Joystick op_joystick = Controllers.getOperatorController();

  }

 
  // public Command getAutonomousCommand() {
  //   // An ExampleCommand will run in autonomous
  //   //return new ScoreAndDriveBackwards();
  // }
}