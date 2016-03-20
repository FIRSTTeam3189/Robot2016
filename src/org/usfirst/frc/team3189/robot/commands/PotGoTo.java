package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.Robot;
import org.usfirst.frc.team3189.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class PotGoTo extends Command {

	double value;
	double desired;

	public PotGoTo(double desired) {
		requires(Robot.elevator);

		this.desired = desired;

	}

	protected void initialize() {

	}

	protected void execute() {
		value = Robot.elevator.getAngle();
		SmartDashboard.putNumber("RealValue", value);

		double difference = desired - value;

		if (difference > 0.0) {
			Robot.elevator.setSpeedSafe(-Constants.ELEVATOR_LIFT_SPEED);
			if (difference < 5.0) {
				Robot.elevator.setSpeedSafe(-Constants.SLOW_ELEVATOR_LIFT_SPEED);
			}
		}
		if (difference < 0.0) {
			Robot.elevator.setSpeedSafe(Constants.ELEVATOR_LOWER_SPEED);
			if (difference < -5.0) {
				Robot.elevator.setSpeedSafe(Constants.SLOW_ELEATOR_LOWER_SPEED);
			}

		}

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if ((value <= desired + Constants.POT_RANGE && value >= desired
				- Constants.POT_RANGE)) {
			return true;
		} else if (Robot.elevator.getLowerLimit()
				&& Robot.elevator.getSpeed() < 0) {
			return true;
		} else if (Robot.elevator.getHigherLimit()
				&& Robot.elevator.getSpeed() > 0) {
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.setSpeedSafe(0);

	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.elevator.setSpeedSafe(0);
	}
}
