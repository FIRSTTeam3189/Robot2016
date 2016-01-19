package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;
import org.usfirst.frc.team3189.robot.subsystems.Shooter;

import com.ni.vision.NIVision.LegFeature;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * 
 * @author Nate, alex, mitch
 *
 */
public class ShootBallCommand extends Command {
/**
 * makes shootBallCommand require objects from shooter
 */
    public ShootBallCommand() {
    	requires(Robot.Shooter);
    	
    }
/**
 * sets motors to full power; sets timeout to 2 seconds
 */
    protected void initialize() {
    	Robot.Shooter.setShooter(1);
    	setTimeout(2);
    }

    protected void execute() {
    }
/**
 * sets isFinished to true after the two second timeOut
 */
    protected boolean isFinished() {
        return isTimedOut();
    }
/**
 * sets shooter motors to off when end is called
 */
    protected void end() {
    	Robot.Shooter.setShooter(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.Shooter.setShooter(0);
    }
}
