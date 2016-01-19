package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**@author nate, mitch, alex
 * declares the shooter motors and assigns them to a value
 */
public class Shooter extends Subsystem {
	/**
	 * declares the motors
	 */
	private CANTalon leftShooterTalon = new CANTalon(RobotMap.leftShooterTalon);
	private CANTalon rightShooterTalon = new CANTalon(RobotMap.rightShooterTalon);
	private Talon leftWindowMotor = new Talon(RobotMap.leftWindowMotor);
	private Talon rightWindowMotor = new Talon(RobotMap.rightWindowMotor);
	/**
	 * assigns the shooter motors to a value
	 * @param speed: the value that the shooter motors are assigned to
	 */
	public void setShooter(double speed) {
		leftShooterTalon.set(speed);
		rightShooterTalon.set(-speed);
	}

	public void initDefaultCommand() {

	}
}
