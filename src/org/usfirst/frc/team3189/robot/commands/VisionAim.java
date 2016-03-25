package org.usfirst.frc.team3189.robot.commands;

import java.awt.Point;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class VisionAim extends Command {
	double currentX;
	double currentY;
	short state = 0;
	double timeout;

	public VisionAim(double timeout) {
		requires(Robot.drivetrain);
		requires(Robot.elevator);
		this.timeout=timeout;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		setTimeout(timeout);
		state = 0;
	}
	
	private boolean Op1(){
		double predictedX = Constants.getPredictedCenterX(Robot.elevator.getAngle());
		if (currentX >= predictedX + Constants.X_DEADZONE) {
			Robot.drivetrain.setspeed(-Constants.AUTO_TURN_SPEED,
					Constants.AUTO_TURN_SPEED);
		}
		// check to turn right
		else if (currentX <= predictedX - Constants.X_DEADZONE) {
			Robot.drivetrain.setspeed(Constants.AUTO_TURN_SPEED,
					-Constants.AUTO_TURN_SPEED);
		}
		// if don't need to turn
		else {
			Robot.drivetrain.setspeed(0, 0);
			return true;
		}
		return false;
	}
	private boolean Op2(){
		double predictedY = Constants.getPredictedCenterY(Robot.elevator.getAngle());
		if (currentY >= predictedY + Constants.Y_DEADZONE) {
			Robot.elevator.setSpeedSafe(-Constants.ELEVATOR_SLOW_LIFT_SPEED);
		} else if (currentY <= predictedY - Constants.Y_DEADZONE) {
			Robot.elevator.setSpeedSafe(Constants.ELEVATOR_SLOW_LOWER_SPEED);
		} else {
			Robot.elevator.setSpeedSafe(0); 
			return true;
		} 
		return false;
	}
	private boolean Op3(){
		double predictedPerimiter = Constants.getPredictedCenterPerimeter(Robot.elevator.getAngle());
		double currentPerimeter = Robot.cam.getPerimeter();
		//if predicted smaller drive forward
		if(predictedPerimiter > currentPerimeter + Constants.P_DEADZONE){
			Robot.drivetrain.setspeed(-Constants.AUTO_FORWARD_SPEED, -Constants.AUTO_FORWARD_SPEED);
		}
		else if(predictedPerimiter <  currentPerimeter - Constants.P_DEADZONE){
			Robot.drivetrain.setspeed(Constants.AUTO_FORWARD_SPEED, Constants.AUTO_FORWARD_SPEED);
		}
		else{
			Robot.drivetrain.setspeed(0, 0); 
			return true;
		} 
		return false;
	}
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Point asdf = Robot.cam.getPoint();
		currentX = asdf.getX();
		currentY = asdf.getY();
		
		if(state == 0){
			if(Op1())
				state++;
		}else if(Op3() && state == 2){
				state++;
		}
		if(Op2() && (state == 1 || state == 3)){
			state++;
		}
		SmartDashboard.putNumber("state", state);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		if (state == 4 || isTimedOut()) {
			return true;
		}
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drivetrain.setspeed(0, 0);
		Robot.elevator.setSpeedSafe(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		Robot.drivetrain.setspeed(0, 0);
		Robot.elevator.setSpeedSafe(0);
	}
}
