package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.utils.Piston;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * provides an interface to use the gearbox of the {@link Drivetrain} for the
 * 2016 team 3189 robot.
 * 
 * @author Alex Rodgers, Nate Mansfield
 */
public class Gearbox extends Subsystem {

	/**
	 * the {@link Piston} controlling the {@link Drivetrain}'s {@link Gearbox}.
	 */
	private Piston pistonLeft = new Piston(RobotMap.gearboxLeftRetract,
			RobotMap.gearboxLeftExtend, false);
	private Piston pistonRight = new Piston(RobotMap.gearboxRightRetract,
			RobotMap.gearboxRightExtend, false);

	/**
	 * toggles the state of the {@link Gearbox}'s {@link Piston}.
	 */
	public void togglePistonState() {
		pistonLeft.toggle();
		pistonRight.toggle();
	}

	/**
	 * sets the {@link Gearbox}'s {@link Piston} to the extended state.
	 */
	public void extendPiston() {
		pistonLeft.extendPiston();
		pistonRight.extendPiston();
	}

	/**
	 * sets the {@link Gearbox}'s {@link Piston} to the retracted state.
	 */
	public void retractPiston() {
		pistonLeft.retractPiston();
		pistonRight.retractPiston();
	}

	/**
	 * sets the right {@link Gearbox}'s {@link Piston} to the extended state.
	 */
	public void extendRightPiston() {
		pistonRight.extendPiston();
	}

	/**
	 * sets the left {@link Gearbox}'s {@link Piston} to the extended state.
	 */
	public void extendLeftPiston() {
		pistonLeft.extendPiston();
	}

	/**
	 * sets the right {@link Gearbox}'s {@link Piston} to the retracted state.
	 */
	public void retractRightPiston() {
		pistonRight.retractPiston();
	}

	/**
	 * sets the left {@link Gearbox}'s {@link Piston} to the retracted state.
	 */
	public void retractLeftPiston() {
		pistonLeft.retractPiston();
	}

	@Override
	public void initDefaultCommand() {
	}
}
