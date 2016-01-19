package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Retracts the piston as soon as drivetrain is initialized
 * 
 * @author Alex
 *
 */
public class DrivetrainInitialize extends Command {
	/**
	 * Depends on the gearbox subsystem
	 */
	public DrivetrainInitialize() {
		requires(Robot.gearbox);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	/**
	 * Piston is retracted as soon as the command is initialized
	 */
	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.gearbox.retractPiston();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	/**
	 * Returns a true value after initialized is called
	 */
	protected boolean isFinished() {
		return true;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}