package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * 
 * @author Alex, Mitch, Nate
 *
 */
public class CompressorStart extends Command {
	/**
	 * Uses compressor subsystem
	 */
	public CompressorStart() {
		requires(Robot.Compressor);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	/**
	 * Starts the compressor, only initialized, not continuous
	 */
	protected void initialize() {
		Robot.Compressor.start();
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	/**
	 * Stops the compressor
	 */
	protected void end() {
		Robot.Compressor.stop();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	/**
	 * Stops the compressor if another command occurs
	 */
	protected void interrupted() {
		Robot.Compressor.stop();
	}
}
