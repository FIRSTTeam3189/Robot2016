package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.Robot;
import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.commands.WindowMotorControl;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Elevator extends Subsystem {
	private Talon windowMotor = new Talon(RobotMap.WindowMotor);

	public Elevator() {
		windowMotor.setInverted(true);
	}

	public boolean setSpeedSafe(double speed) {
		//windowMotor.enableForwardSoftLimit(windowMotor.isFwdLimitSwitchClosed());
		//windowMotor.enableReverseSoftLimit(windowMotor.isRevLimitSwitchClosed());
		if (getPot() <= Constants.POT_UPPER && speed < 0) {
			windowMotor.set(0);
			return false;
		} else if (getPot() >= Constants.POT_LOWER && speed > 0) {
			windowMotor.set(0);
			return false;
		}else{
			windowMotor.set(speed);
		}
		
		return true;
	}
	
	public boolean setSpeed(double speed) {
		/*windowMotor.enableForwardSoftLimit(windowMotor.isFwdLimitSwitchClosed());
		windowMotor.enableReverseSoftLimit(windowMotor.isRevLimitSwitchClosed());
		if (getPot() <= Constants.POT_UPPER && speed < 0) {
			speed = 0;
			return false;
		} else if (getPot() >= Constants.POT_LOWER && speed > 0) {
			speed = 0;
			return false;
		}
		windowMotor.set(speed);*/
		return true;
	}

	public double getSpeed() {
		return windowMotor.get();
	}

	public boolean getLowerLimit() {
		return false; //windowMotor.isRevLimitSwitchClosed();
	}

	public boolean getHigherLimit() {
		return false;//windowMotor.isFwdLimitSwitchClosed();
	}

	public double getPot() {
		return Robot.shooter.getPot();//windowMotor.getAnalogInRaw();
	}

	public double getAngle() {
		return Constants.ELEVATOR_HIGHEST_ANGLE
				+ ((Constants.ANGLE_SPAN * (Constants.POT_UPPER - getPot())) / Constants.POT_SPAN);
	}

	public void setHigh() {
		Constants.POT_UPPER = getPot();
	}

	public void setLow() {
		Constants.POT_LOWER = getPot();
	}

	public boolean limited() {
		return getHigherLimit() || getLowerLimit();
	}

	public void initDefaultCommand() {
		setDefaultCommand(new WindowMotorControl());
	}

	public void updateStatus() {
		SmartDashboard.putNumber("Elevator Angle", getAngle());
		SmartDashboard.putNumber("Elevator Pot", getPot());
		SmartDashboard.putBoolean("Upper Switch", getHigherLimit());
		SmartDashboard.putBoolean("Lower Switch", getLowerLimit());
	}
}
