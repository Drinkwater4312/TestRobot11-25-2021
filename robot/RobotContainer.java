// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // Subsystems
  private final PneumaticSubsystem m_pnuematicSubsystem = new PneumaticSubsystem();

  private final DrivetrainSub m_drivetrainSub = new DrivetrainSub();
  
  private final AutoDriveSub m_autoDriveSub = new AutoDriveSub();


  // Commands 
  private final AutoDriveCommand m_autoDriveCommand = new AutoDriveCommand(m_autoDriveSub);

  private final PneumaticCommand m_pneumaticCommand = new PneumaticCommand(m_pnuematicSubsystem);


  // Joysticks
  private final Joystick driveJoystick = new Joystick(Constants.DRIVE_JOYSTICK_PORT);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    // The drive command is a default command, the two axes are x and y (maybe respectively idk) and the third is sensitivity

    // *NOTE* currently this command-subsystem set does nothing, once we know what motors we are working with we can do something.
    m_drivetrainSub.setDefaultCommand(
      new DriveCommand(m_drivetrainSub, 
      () -> -driveJoystick.getRawAxis(1), 
      () -> -driveJoystick.getRawAxis(2),
      () -> driveJoystick.getRawAxis(3)));
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton pneumaticButton = new JoystickButton(driveJoystick, Constants.PNEUMATIC_BUTTON);
    pneumaticButton.whenHeld(m_pneumaticCommand, Constants.INTERRUPTIBLE);
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // *NOTE* This command-subsystem set is currently empty
    return m_autoDriveCommand;
  }
}
