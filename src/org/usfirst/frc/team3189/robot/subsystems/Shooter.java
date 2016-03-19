package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.commands.ShooterControll;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	public DigitalInput ballIn = new DigitalInput(RobotMap.ballSwitch);
	
	public Shooter(){
		rightShooterTalon.setInverted(false);
		leftShooterTalon.setInverted(false);
		rightShooterTalon.enableBrakeMode(true);
		leftShooterTalon.enableBrakeMode(true);
	}

	/**
	 * sets the speed of the {@link Shooter}'s wheels.
	 * 
	 * @param speed
	 *            the desired speed to set the wheels to between -1 and 1,
	 *            Negative being reversed.
	 */
	public void setShooter(double speed) {
		leftShooterTalon.set(speed);
		rightShooterTalon.set(speed);
	}
	
	public double getPot() {
		return rightShooterTalon.getAnalogInRaw();
	}

	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ShooterControll());
	}
	
	public void updateStatus(){
		SmartDashboard.putData(this);
		SmartDashboard.putNumber("left Shooter Out V", leftShooterTalon.getOutputVoltage());
		SmartDashboard.putNumber("left Shooter Out C", leftShooterTalon.getOutputCurrent());
		SmartDashboard.putNumber("left Shooter Bus V", leftShooterTalon.getBusVoltage());
		SmartDashboard.putNumber("right Shooter Out V", rightShooterTalon.getOutputVoltage());
		SmartDashboard.putNumber("right Shooter Out C", rightShooterTalon.getOutputCurrent());
		SmartDashboard.putNumber("right Shooter Bus V", rightShooterTalon.getBusVoltage());
		SmartDashboard.putBoolean("Ball In", ballIn.get());
		
	}
}
