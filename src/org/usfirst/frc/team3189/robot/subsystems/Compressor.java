 package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.commands.CompressorStart;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * provides an interface for the compresser for the 2016 team 3189 robot.
 * 
 * @author Mitch
 *
 */
public class Compressor extends Subsystem {

	/**the {@link edu.wpi.first.wpilibj.Compressor} for the robot.*/
	private edu.wpi.first.wpilibj.Compressor compressor = new edu.wpi.first.wpilibj.Compressor();

	/**
	 * Starts the {@link edu.wpi.first.wpilibj.Compressor}.
	 */
	public void start() {
		compressor.start();
	}

	/**
	 * Stops the {@link edu.wpi.first.wpilibj.Compressor}.
	 */
	public void stop() {
		compressor.stop();
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new CompressorStart());
	}
}
