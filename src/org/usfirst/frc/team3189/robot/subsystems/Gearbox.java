package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.utils.Piston;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * provides an interface to use the gearbox of the {@link Drivetrain} for the
 * 2016 team 3189 robot.
 * 
 * @author Alex Rodgers
 */
public class Gearbox extends Subsystem {

	/**
	 * the {@link Piston} controlling the {@link Drivetrain}'s {@link Gearbox}.
	 */
	private Piston piston = new Piston(RobotMap.gearboxRetract, RobotMap.gearboxExtend);

	/**
	 * toggles the state of the {@link Gearbox}'s {@link Piston}.
	 */
	public void togglePistonState() {
		piston.toggle();
	}

	/**
	 * sets the {@link Gearbox}'s {@link Piston} to the extended state.
	 */
	public void extendPiston() {
		piston.extendPiston();
	}

	/**
	 * sets the {@link Gearbox}'s {@link Piston} to the retracted state.
	 */
	public void retractPiston() {
		piston.retractPiston();
	}

	@Override
	public void initDefaultCommand() {
	}
}
