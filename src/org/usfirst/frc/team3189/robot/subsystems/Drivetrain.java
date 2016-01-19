package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.commands.DrivetrainControl;
import org.usfirst.frc.team3189.robot.utils.Piston;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * provides an interface for the drivetrain of the 2016 team 3189 robot.
 * 
 * @author Nate, Alex, Mitch
 */
public class Drivetrain extends Subsystem {

	/**
	 * the {@link SpeedController} for the front left motor of the
	 * {@link Drivetrain}
	 */
	private CANTalon leftFrontMotor = new CANTalon(RobotMap.leftfrontMotor);
	/**
	 * the {@link SpeedController} for the front right motor of the
	 * {@link Drivetrain}
	 */
	private CANTalon rightFrontMotor = new CANTalon(RobotMap.rightfrontMotor);
	/**
	 * the {@link SpeedController} for the back left motor of the
	 * {@link Drivetrain}
	 */
	private CANTalon leftBackMotor = new CANTalon(RobotMap.leftbackMotor);
	/**
	 * the {@link SpeedController} for the back right motor of the
	 * {@link Drivetrain}
	 */
	private CANTalon rightBackMotor = new CANTalon(RobotMap.rightbackMotor);

	/**
	 * sets the speed of the drive wheels of the {@link Drivetrain}.
	 * 
	 * @param left
	 *            the desired speed to set the left wheels of the
	 *            {@link Drivetrain} to between -1 and 1, negitive being
	 *            reversed.
	 * @param right
	 *            the desired speed to set the right wheels of the
	 *            {@link Drivetrain} to between -1 and 1, negitive being
	 *            reversed.
	 */
	public void setspeed(double left, double right) {
		leftFrontMotor.set(left);
		rightFrontMotor.set(-right);
		leftBackMotor.set(left);
		rightBackMotor.set(-right);
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DrivetrainControl());
	}
}
