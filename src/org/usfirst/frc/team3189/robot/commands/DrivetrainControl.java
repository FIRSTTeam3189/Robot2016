package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.OI;
import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets the speed of the robot using a tank drive control based on the Y axis of
 * the {@link Robot}'s {@link OI}'s 0th and 1st {@link Joystick}'s
 * 
 * @author Mitch, Nate, Alex
 *
 */
public class DrivetrainControl extends Command {

	public DrivetrainControl() {
		requires(Robot.drivetrain);

	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.drivetrain.setspeed(Robot.oi.getLeftJoystickY(), Robot.oi.getRightJoystickY());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		Robot.drivetrain.setspeed(0, 0);
	}
}
