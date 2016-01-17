package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainControl extends Command {

	public DrivetrainControl() {
		requires(Robot.drivetrain);

		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.drivetrain.setspeed(Robot.oi.getLeftJoystickY(), Robot.oi.getRightJoystickY());
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.setspeed(0, 0);
	}
}
