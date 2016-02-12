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
public class DrivetrainReverse extends Command {

	public DrivetrainReverse() {
		requires(Robot.drivetrain);

	}

	@Override
	protected void initialize() {
		Robot.drivetrain.invert();
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		Robot.drivetrain.setspeed(0, 0);
	}
}
