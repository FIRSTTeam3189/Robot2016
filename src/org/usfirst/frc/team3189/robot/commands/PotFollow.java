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
		value = (Robot.elevator.getPot() - Constants.potMin) / (Constants.potMax - Constants.potMin);
		SmartDashboard.putNumber("RealValue", value);
		/**
		 * raises elevator motor if value is lower than wanted
		 */
		if (value <= Robot.oi.getThrottle() - Constants.potRange) {
			Robot.elevator.setSpeed(Constants.elevatorLiftSpeed);
			
			SmartDashboard.putBoolean("up", true);
			SmartDashboard.putBoolean("Down", false);
			/**
			 * lowers elevator motor if value is higher than wanted
			 */
		} else if (value >= Robot.oi.getThrottle() + Constants.potRange) {
			Robot.elevator.setSpeed(-Constants.elevatorLowerSpeed);

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
		if(value <= Robot.oi.getThrottle() + Constants.potRange && value >= Robot.oi.getThrottle() - Constants.potRange) {
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.elevator.setSpeed(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.elevator.setSpeed(0);
	}
}
