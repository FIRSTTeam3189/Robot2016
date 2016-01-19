package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * a {@link Command} used to start the compressor, and stop when ended.
 * 
 * @author Alex, Mitch, Nate
 */
public class CompressorStart extends Command {

	public CompressorStart() {
		requires(Robot.Compressor);
	}

	@Override
	protected void initialize() {
		Robot.Compressor.start();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.Compressor.stop();
	}

	@Override
	protected void interrupted() {
		Robot.Compressor.stop();
	}
}
