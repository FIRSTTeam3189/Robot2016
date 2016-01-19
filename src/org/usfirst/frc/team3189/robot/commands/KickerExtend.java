package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;
import org.usfirst.frc.team3189.robot.subsystems.Kicker;

import edu.wpi.first.wpilibj.command.Command;
/**
 * command to extend the shooter piston
 * @author Mitch
 *
 */
public class KickerExtend extends Command {

    public KickerExtend() {
    	requires(Robot.Kicker);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

/*
 * extends the piston
 */
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.Kicker.extend();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
