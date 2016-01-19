package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * provides an interfaces to use the shooter wheels and the rising/lowering
 * system of the 2016 team 3189 robot.
 * 
 * @author nate, mitch, alex
 */
public class Shooter extends Subsystem {

	/** the left shooter wheel */
	private CANTalon leftShooterTalon = new CANTalon(RobotMap.leftShooterTalon);
	/** the right shooter wheel */
	private CANTalon rightShooterTalon = new CANTalon(RobotMap.rightShooterTalon);

	/**
	 * sets the speed of the {@link Shooter}'s wheels.
	 * 
	 * @param speed
	 *            the desired speed to set the wheels to between -1 and 1,
	 *            Negative being reversed.
	 */
	public void setShooter(double speed) {
		leftShooterTalon.set(speed);
		rightShooterTalon.set(-speed);
	}

	@Override
	public void initDefaultCommand() {
	}
}
