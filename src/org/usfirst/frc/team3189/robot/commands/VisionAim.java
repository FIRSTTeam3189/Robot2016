package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VisionAim extends Command {
	double currentX;
	double currentY;

	public VisionAim() {
		requires(Robot.drivetrain);
		requires(Robot.elevator);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double currentX = 0.5;
		double currentY = 0.5;

		// check to turn left
		if (currentX >= Constants.DESIRED_X + Constants.X_DEADZONE) {
			Robot.drivetrain.setspeed(-Constants.AUTO_TURN_SPEED,
					Constants.AUTO_TURN_SPEED);
		}
		// check to turn right
		else if (currentX <= Constants.DESIRED_X - Constants.X_DEADZONE) {
			Robot.drivetrain.setspeed(Constants.AUTO_TURN_SPEED,
					-Constants.AUTO_TURN_SPEED);
		}
		// if don't need to turn
		else {
			Robot.drivetrain.setspeed(0, 0);
		}

		if (currentY >= Constants.DESIRED_Y + Constants.Y_DEADZONE) {
			Robot.elevator.setSpeedSafe(-Constants.ELEVATOR_SLOW_LIFT_SPEED);
		} else if (currentY <= Constants.DESIRED_Y - Constants.Y_DEADZONE) {
			Robot.elevator.setSpeedSafe(Constants.ELEVATOR_SLOW_LOWER_SPEED);
		} else {
			Robot.elevator.setSpeedSafe(0);
		}
		this.currentX = currentX;
		this.currentY = currentY;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (Math.abs(currentX - Constants.DESIRED_X) < Constants.X_DEADZONE
				&& Math.abs(currentY - Constants.DESIRED_Y) < Constants.DEAD_ZONE) {
			return true;
		} else
			return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.setspeed(0, 0);
		Robot.elevator.setSpeedSafe(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.setspeed(0, 0);
		Robot.elevator.setSpeedSafe(0);
	}
}
