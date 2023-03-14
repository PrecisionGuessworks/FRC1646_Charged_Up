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
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.lib.Controllers;
import frc.robot.Subsystems.Arm.ArmSubsystem;
import frc.robot.Subsystems.Arm.states.ManualArmState;
import frc.robot.Subsystems.Blinkin.BlinkinState;
import frc.robot.Subsystems.Blinkin.BlinkinSubsystem;
import frc.robot.Subsystems.Drivetrain.DrivetrainSubsystem;
import frc.robot.Subsystems.Drivetrain.states.OpenLoopState;
import frc.robot.Subsystems.Intake.IntakeSubsystem;
import frc.robot.Subsystems.Intake.states.ManualIntakeState;
import frc.robot.Subsystems.Wrist.WristSubsystem;
import frc.robot.Subsystems.Wrist.states.ManualWristState;
import frc.robot.commands.HighCubeWithChargingStation;
import frc.robot.commands.autonomous.DriveBackwardsAutoCableBerm;
import frc.robot.commands.autonomous.HighCube;
import frc.robot.commands.autonomous.HighCubeNoBerm;
import frc.robot.commands.autonomous.MidCubeAuto;
import frc.robot.commands.autonomous.MidCubeNoBerm;
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
  SendableChooser<Command> autoPicker;

  DrivetrainSubsystem drive;
  ArmSubsystem arm;
  WristSubsystem wrist;
  IntakeSubsystem intake;
  PowerDistribution powerDistrubutionBoard;
  BlinkinSubsystem blinkin;


  public RobotContainer() {
    initilizeSubsystems();
    initilizePowerDistrubutionBoard();
    setAllDefaultCommands();
    configureButtonBindings();
    configureAutoPicker();
  }

  public void configureAutoPicker(){
    autoPicker.setDefaultOption("Drive Backwards", new DriveBackwardsAutoCableBerm());
    autoPicker.addOption("Mid Cube - NO drive", new MidCubeAuto());
    autoPicker.addOption("Mid Cube - YES Drive", new MidCubeNoBerm());
    autoPicker.addOption("High Cube - NO Drive", new HighCube());
    autoPicker.addOption("High Cube - YES Drive", new HighCubeNoBerm());
    autoPicker.addOption("High Cube - Charging Station", new HighCubeWithChargingStation());
    SmartDashboard.putData(autoPicker);
  }

  public void initilizeSubsystems(){
    drive = DrivetrainSubsystem.getInstance();
    arm = ArmSubsystem.getInstance();
    wrist = WristSubsystem.getInstance();
    intake = IntakeSubsystem.getInstance();
    blinkin = BlinkinSubsystem.getInstance();
    autoPicker = new SendableChooser<>();
  }

  public void initilizePowerDistrubutionBoard(){
    powerDistrubutionBoard = new PowerDistribution(1, ModuleType.kRev);
    powerDistrubutionBoard.clearStickyFaults();
  }

  public void setAllDefaultCommands(){
    setDefaultCommand(drive, new OpenLoopState());
    setDefaultCommand(arm, new ManualArmState());
    setDefaultCommand(wrist, new ManualWristState());
    setDefaultCommand(intake, new ManualIntakeState());
    setDefaultCommand(blinkin, new BlinkinState());
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
  public Command getAutonomousCommand() {
    return autoPicker.getSelected();
  }



}