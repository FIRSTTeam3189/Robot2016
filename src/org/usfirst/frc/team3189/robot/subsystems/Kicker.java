package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.utils.Piston;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * provides an interface for the {@link Shooter} {@link Piston} used to push the
 * boulder into the wheels for the 2016 team 3189 robot.
 * 
 * @author Mitch
 */
public class Kicker extends Subsystem {

	/** the {@link Piston} controlling the {@link Kicker} */
	private Piston shooterPiston = new Piston(RobotMap.shooterRetract, RobotMap.shooterExtend);

	/**
	 * calls the retractPiston method inside the {@link Kicker}'s {@link Piston}
	 * .
	 */
	public void retract() {
		shooterPiston.retractPiston();
	}

	/**
	 * calls the extendPiston method inside the {@link Kicker}'s {@link Piston}.
	 */
	public void extend() {
		shooterPiston.extendPiston();
	}

	@Override
	public void initDefaultCommand() {
	}
}
