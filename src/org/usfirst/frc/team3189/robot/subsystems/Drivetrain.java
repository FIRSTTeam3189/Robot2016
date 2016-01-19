package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.commands.DrivetrainControl;
import org.usfirst.frc.team3189.robot.utils.Piston;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Holds the interface for the drivetrain.
 * 
 * @author Nate, Alex, Mitch
 *
 */
public class Drivetrain extends Subsystem {
	private CANTalon leftFrontMotor = new CANTalon(RobotMap.leftfrontMotor);
	private CANTalon rightFrontMotor = new CANTalon(RobotMap.rightfrontMotor);
	private CANTalon leftBackMotor = new CANTalon(RobotMap.leftbackMotor);
	private CANTalon rightBackMotor = new CANTalon(RobotMap.rightbackMotor);

	/**
	 * 
	 * @param left
	 *            value of the left side of the robot
	 * @param right
	 *            value of the right side of the robot
	 */
	public void setspeed(double left, double right) {
		leftFrontMotor.set(left);
		rightFrontMotor.set(-right);
		leftBackMotor.set(left);
		rightBackMotor.set(-right);
	}

	/**
	 * starts the drivetrain control
	 */
	
	public void initDefaultCommand() {
		setDefaultCommand(new DrivetrainControl());
	}
}
