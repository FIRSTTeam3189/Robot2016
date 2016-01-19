package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * command to intake a ball
 * @author Nate
 *
 */
public class IntakeBall extends Command {

    public IntakeBall() {
    	requires(Robot.Shooter);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
/*
 * sets the shooter vale and time
 */
    protected void initialize() {
    	Robot.Shooter.setShooter(-0.25);
    	setTimeout(2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

/*
 * sets shooter to 0 when the command is finished
 */
    // Called once after isFinished returns true
    protected void end() {
    	Robot.Shooter.setShooter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.Shooter.setShooter(0);
    }
}
