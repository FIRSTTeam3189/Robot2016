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
	
	public void getLowerLimit(){
		windowMotor.isRevLimitSwitchClosed();
	}
	
	public void getHigherLimit(){
		windowMotor.isFwdLimitSwitchClosed();
	}
	
	public int getPot(){
		return windowMotor.getAnalogInRaw();
	}
	
	public double getAngle(){
		return (getPot() - Constants.POT_VALUE_AT_ZERO) / Constants.POINTS_PER_DEGREE;
	}
	
	public void setZero(){
		Constants.POT_VALUE_AT_ZERO = getPot();
	}
	
	public void setHigh(){
		Constants.POT_MAX = getPot();
	}
	
	public void setLow(){
		Constants.POT_MIN = getPot();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new WindowMotorControl());
	}
	
	public void updateStatus(){
		SmartDashboard.putNumber("Elevator Angle", getAngle());
	}
}
