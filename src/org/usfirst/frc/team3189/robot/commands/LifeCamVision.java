package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LifeCamVision extends Command {

	public LifeCamVision() {
		requires(Robot.cam);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		if(!Robot.cam.usingLifeCam()){
			try {
				Robot.cam.start();
			} catch (Exception e) {

			}
			Robot.cam.useLifeCam();
		}
		else {
			try {
				Robot.cam.end();
			} catch (Exception e) {

			}
			Robot.cam.useKinect();
		}
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		//Robot.cam.release();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
