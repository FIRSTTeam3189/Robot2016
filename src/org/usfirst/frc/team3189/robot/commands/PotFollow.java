package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */

public class PotFollow extends Command {

	double value;

	public PotFollow() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.drivetrain);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		// The raw input of the potentiometer is not the actual value. This is
		// how we determine what it really is.
		value = (Robot.drivetrain.getPot() - Robot.drivetrain.min) / (Robot.drivetrain.max - Robot.drivetrain.min);
		SmartDashboard.putNumber("RealValue", value);
		
		if (value <= Robot.oi.getThrottle() - Robot.drivetrain.range) {
			Robot.drivetrain.setspeed(-0.40, -0.40);
			
			SmartDashboard.putBoolean("up", true);
			SmartDashboard.putBoolean("Down", false);
		} else if (value >= Robot.oi.getThrottle() + Robot.drivetrain.range) {
			Robot.drivetrain.setspeed(0.30, 0.30);

			SmartDashboard.putBoolean("up", false);
			SmartDashboard.putBoolean("down", true);

		} else {

			SmartDashboard.putBoolean("up", false);
			SmartDashboard.putBoolean("down", false);
		}

	}
	
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if(value <= Robot.oi.getThrottle() + Robot.drivetrain.range && value >= Robot.oi.getThrottle() - Robot.drivetrain.range) {
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.setspeed(0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.setspeed(0, 0);
	}
}
