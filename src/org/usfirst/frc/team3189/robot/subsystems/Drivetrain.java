package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.commands.DrivetrainControl;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Drivetrain extends Subsystem {
	private Talon leftFrontMotor = new Talon(RobotMap.leftfrontMotor);
	private Talon rightFrontMotor = new Talon(RobotMap.rightfrontMotor);
	private Talon leftBackMotor = new Talon(RobotMap.leftbackMotor);
	private Talon rightBackMotor = new Talon(RobotMap.rightbackMotor);

	public void setspeed(double left, double right) {
		leftFrontMotor.set(left);
		rightFrontMotor.set(-right);
		leftBackMotor.set(left);
		rightBackMotor.set(-right);
	}
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		setDefaultCommand(new DrivetrainControl());
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
