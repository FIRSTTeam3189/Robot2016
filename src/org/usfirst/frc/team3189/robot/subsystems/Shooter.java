package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Ultrasonic;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Shooter extends Subsystem {
    
	private CANTalon leftShooterTalon = new CANTalon(RobotMap.leftShooterTalon);
	private CANTalon rightShooterTalon = new CANTalon(RobotMap.rightShooterTalon);	
	
	public void setShooter(double speed){
		 leftShooterTalon.set(speed);
		 rightShooterTalon.set(-speed);
	}

    public void initDefaultCommand() {
    	
    }
}

