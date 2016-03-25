package org.usfirst.frc.team3189.robot.autonomous;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**turns the robot right during autonomous
 *@author Alex
 */
public class TurnRight extends Command {
	private double seconds;
    public TurnRight(double seconds) {
    	requires(Robot.drivetrain);
    	this.seconds = seconds;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(Constants.AUTO_TURN_TIME);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.drivetrain.setspeed(-Constants.AUTO_TURN_SPEED, Constants.AUTO_TURN_SPEED);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.setspeed(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.setspeed(0, 0);
    }
}
