package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.commands.WindowMotorControl;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Elevator extends Subsystem {
	private CANTalon windowMotor = new CANTalon(RobotMap.WindowMotor);

	public Elevator(){
		windowMotor.setInverted(true);
	}
	
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public void setSpeed(double speed) {
		windowMotor.set(speed);
	}
	
	public double getPot(){
		return windowMotor.getAnalogInRaw();
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new WindowMotorControl());
	}
}
