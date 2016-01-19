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
	}

	/**
	 * Piston is retracted as soon as the command is initialized
	 */
	protected void initialize() {
		Robot.gearbox.retractPiston();
	}

	protected void execute() {
	}

	/**
	 * Returns a true value after initialized is called
	 */
	protected boolean isFinished() {
		return true;
	}

	protected void end() {
	}

	protected void interrupted() {
	}
}
