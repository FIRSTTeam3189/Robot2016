package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DrivetrainControl extends Command {

	public DrivetrainControl() {
		requires(Robot.drivetrain);

	}

	protected void initialize() {
	}

	protected void execute() {
		Robot.drivetrain.setspeed(Robot.oi.getLeftJoystickY(), Robot.oi.getRightJoystickY());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	protected void interrupted() {
		Robot.drivetrain.setspeed(0, 0);
	}
}
