package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Sets the speed of the robot using a tankdrive control
 * 
 * @author Mitch, Nate, Alex
 *
 */
public class DrivetrainControl extends Command {
	/**
	 * Accesses drivetrain located in robot
	 */
	public DrivetrainControl() {
		requires(Robot.drivetrain);

	}

	protected void initialize() {
	}

	/**
	 * Always gets the input from both joysticks to set the speed of the left
	 * and right side of the robot, controlling the speed
	 */
	protected void execute() {
		Robot.drivetrain.setspeed(Robot.oi.getLeftJoystickY(), Robot.oi.getRightJoystickY());
	}

	protected boolean isFinished() {
		return false;
	}

	protected void end() {
	}

	/**
	 * Sets the speed to 0 in case another command needs to use the robot
	 */
	protected void interrupted() {
		Robot.drivetrain.setspeed(0, 0);
	}
}
