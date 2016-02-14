package org.usfirst.frc.team3189.robot.autonomous;

import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutonomousShooter extends Command {

	
	private double speed = .3;
    public AutonomousShooter(double seconds) {
    	requires(Robot.drivetrain);
    	setTimeout(seconds);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.Shooter.setShooter(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.Shooter.setShooter(0);
    }
}
