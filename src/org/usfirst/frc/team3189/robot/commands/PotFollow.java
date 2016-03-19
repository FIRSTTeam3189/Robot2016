package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.OI;
import org.usfirst.frc.team3189.robot.Robot;
import org.usfirst.frc.team3189.robot.subsystems.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**sets the position {@link Elevator} motors to the percent of getThrottle in {@link OI}
 *@author Dev
 */

public class PotFollow extends Command {
	double value;

	public PotFollow() {
		requires(Robot.elevator);
	}

	protected void initialize() {
	}

	protected void execute() {
		/**
		 * sets value to current percentage of where to
		 *  window motor is positioned compared to its maximum and minium position
		 */
		value = (Robot.elevator.getPot() - Constants.POT_UPPER) / (Constants.POT_LOWER - Constants.POT_UPPER);
		SmartDashboard.putNumber("RealValue", value);
		/**
		 * raises elevator motor if value is lower than wanted
		 */
		if (value <= Robot.oi.getThrottle() - Constants.POT_RANGE) {
			Robot.elevator.setSpeedSafe(Constants.ELEVATOR_LIFT_SPEED);
			
			SmartDashboard.putBoolean("up", true);
			SmartDashboard.putBoolean("Down", false);
			/**
			 * lowers elevator motor if value is higher than wanted
			 */
		} else if (value >= Robot.oi.getThrottle() + Constants.POT_RANGE) {
			Robot.elevator.setSpeedSafe(-Constants.ELEVATOR_LOWER_SPEED);

			SmartDashboard.putBoolean("up", false);
			SmartDashboard.putBoolean("down", true);

		} else {

			SmartDashboard.putBoolean("up", false);
			SmartDashboard.putBoolean("down", false);
		}

	}
	
	// Make this return true when this Command no longer needs to run execute()
	/**
	 * checks whether value is where it is wanted, and returns true if it is.
	 * 
	 * it is checking whether we need to move the motor and sets the speed to zero if we are.
	 */
	protected boolean isFinished() {
		if(value <= Robot.oi.getThrottle() + Constants.POT_RANGE && value >= Robot.oi.getThrottle() - Constants.POT_RANGE) {
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
