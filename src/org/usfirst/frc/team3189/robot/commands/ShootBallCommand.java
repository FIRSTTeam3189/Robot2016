package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;
import org.usfirst.frc.team3189.robot.subsystems.Shooter;

import com.ni.vision.NIVision.LegFeature;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * 
 * @author Nate
 *
 */
public class ShootBallCommand extends Command {

    public ShootBallCommand() {
    	requires(Robot.Shooter);
    	
    }

    protected void initialize() {
    	Robot.Shooter.setShooter(1);
    	setTimeout(2);
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return isTimedOut();
    }

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
