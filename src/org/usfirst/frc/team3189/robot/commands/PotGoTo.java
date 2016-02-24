package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.Robot;

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
		value = (Robot.elevator.getPot() - Constants.POT_MIN) / (Constants.POT_MAX - Constants.POT_MIN);
		SmartDashboard.putNumber("RealValue", value);
		
		if (value <= desired - Constants.POT_RANGE) {
			Robot.elevator.setSpeed(Constants.ELEVATOR_LIFT_SPEED);
			
			SmartDashboard.putBoolean("up", true);
			SmartDashboard.putBoolean("Down", false);
		} else if (value >= desired + Constants.POT_RANGE) {
			Robot.elevator.setSpeed(-Constants.ELEVATOR_LOWER_SPEED);

			SmartDashboard.putBoolean("up", false);
			SmartDashboard.putBoolean("down", true);

		} else {

			SmartDashboard.putBoolean("up", false);
			SmartDashboard.putBoolean("down", false);
		}

	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(value <= desired + Constants.POT_RANGE && value >= desired - Constants.POT_RANGE) {
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
