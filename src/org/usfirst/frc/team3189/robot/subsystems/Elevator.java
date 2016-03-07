package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.commands.WindowMotorControl;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {
	private CANTalon windowMotor = new CANTalon(RobotMap.WindowMotor);

	public Elevator(){
		windowMotor.setInverted(true);
	}
	
	public void setSpeed(double speed) {
		windowMotor.enableForwardSoftLimit(windowMotor.isFwdLimitSwitchClosed());
		windowMotor.enableReverseSoftLimit(windowMotor.isRevLimitSwitchClosed());
		windowMotor.set(speed);
	}
	
	public double getSpeed(){
		return windowMotor.get();
	}
	
	public boolean getLowerLimit(){
		return windowMotor.isRevLimitSwitchClosed();
	}
	
	public boolean getHigherLimit(){
		return windowMotor.isFwdLimitSwitchClosed();
	}
	
	public double getPot(){
		return windowMotor.getAnalogInRaw();
	}
	
	public double getAngle(){
		return Constants.ELEVATOR_HIGHEST_ANGLE + ((Constants.ANGLE_SPAN * (Constants.POT_UPPER - getPot()))/Constants.POT_SPAN);
	}
	
	public void setHigh(){
		Constants.POT_UPPER = getPot();
	}
	
	public void setLow(){
		Constants.POT_LOWER = getPot();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new WindowMotorControl());
	}
	
	public void updateStatus(){
		SmartDashboard.putNumber("Elevator Angle", getAngle());
	}
}
