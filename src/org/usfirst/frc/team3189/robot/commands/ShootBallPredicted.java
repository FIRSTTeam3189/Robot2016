package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.Robot;
import org.usfirst.frc.team3189.robot.subsystems.VisionDisplay;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ShootBallPredicted extends Command {
	private long begTime;
	
	public ShootBallPredicted() {
		requires(Robot.kicker);
		requires(Robot.shooter);
	}
	@Override
	protected void initialize() {
		setTimeout(3);
		begTime = System.currentTimeMillis();

	}
	@Override
	protected void execute() {
		double thing = Constants.getPredictedSpeed(Robot.elevator.getAngle());
		if(Robot.elevator.getAngle() < 30)
			thing = 0.3;
		if (begTime + 2000 > System.currentTimeMillis()) {
			Robot.shooter.setShooter(thing);
		} else if (Robot.kicker.isRetracted() && begTime + 2000 <= System.currentTimeMillis()) {
			
			Robot.kicker.extend();

		}
	}
	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.shooter.setShooter(0);
		Robot.kicker.retract();
		SmartDashboard.putNumber("LastP", Robot.cam.getPerimeter());
		SmartDashboard.putNumber("LastA", Robot.elevator.getAngle());
		SmartDashboard.putNumber("LastX", Robot.cam.getCenterX());
		SmartDashboard.putNumber("LastY", Robot.cam.getCenterY());
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		Robot.shooter.setShooter(0);
		Robot.kicker.retract();
	}
}
