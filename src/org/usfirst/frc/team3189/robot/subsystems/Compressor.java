 package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.commands.CompressorStart;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Adds the compressor to the robot. Also allows to stop and start the
 * compressor
 * 
 * @author Mitch
 *
 */
public class Compressor extends Subsystem {

	private edu.wpi.first.wpilibj.Compressor compressor = new edu.wpi.first.wpilibj.Compressor();

	/**
	 * Starts the compressor as the default command
	 */
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CompressorStart());

	}

	/**
	 * Starts the compressor
	 */
	public void start() {
		compressor.start();
	}

	/**
	 * Stops the compressor
	 */
	public void stop() {
		compressor.stop();
	}

}
