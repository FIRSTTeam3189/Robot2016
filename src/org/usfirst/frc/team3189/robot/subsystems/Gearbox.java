package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.utils.Piston;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Gearbox extends Subsystem {

	private Piston piston = new Piston(RobotMap.gearboxRetract, RobotMap.gearboxExtend);
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void togglePistonState() {
		piston.toggle();
	}

	public void extendPiston() {
		piston.extendPiston();
	}

	public void retractPiston() {
		piston.retractPiston();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
