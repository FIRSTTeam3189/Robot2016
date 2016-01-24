package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class WindowMotors extends Subsystem {
	private CANTalon rightWindowMotor = new CANTalon(RobotMap.rightWindowMotor);
	private CANTalon leftWindowMotor = new CANTalon(RobotMap.leftWindowMotor);    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public void setWindowSpeed(double speed){
		rightWindowMotor.set(speed);
		leftWindowMotor.set(speed);
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

