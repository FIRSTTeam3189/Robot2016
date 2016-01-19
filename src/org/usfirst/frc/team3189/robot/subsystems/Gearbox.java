package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.utils.Piston;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Controls the gearbox for the drivetrain
 * 
 * @author Alex Rodgers
 *
 */
public class Gearbox extends Subsystem {

	private Piston piston = new Piston(RobotMap.gearboxRetract, RobotMap.gearboxExtend);

	/**
	 * toggles the state of the gearbox piston
	 */
	public void togglePistonState() {
		piston.toggle();
	}

	/**
	 * extends the gearbox piston
	 */
	public void extendPiston() {
		piston.extendPiston();
	}

	/**
	 * retracts the gearbox piston
	 */
	public void retractPiston() {
		piston.retractPiston();
	}

	public void initDefaultCommand() {
	}
}
