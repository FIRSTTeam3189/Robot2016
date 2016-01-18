package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.commands.CompressorStart;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Compressor extends Subsystem {

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	private edu.wpi.first.wpilibj.Compressor compressor = new edu.wpi.first.wpilibj.Compressor();

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CompressorStart());

	}

	public void start() {
		compressor.start();
	}

	public void stop() {
		compressor.stop();
	}

}
