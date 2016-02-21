package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.OI;
import org.usfirst.frc.team3189.robot.Robot;
import org.usfirst.frc.team3189.robot.subsystems.Drivetrain;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

/**
 *reverses the input of the {@link Joystick}s at port 0 and 1 at {@link OI} for the setspeed method
 * of the{@link Drivetrain} 
 * 
 * makes the robot drive backwards
 * 
 * @author Alex
 *
 */
public class DrivetrainReverse extends Command {

	public DrivetrainReverse() {
		requires(Robot.drivetrain);

	}

	@Override
	protected void initialize() {
		Robot.drivetrain.invert();
	}

	@Override
	protected void execute() {
		
	}

	@Override
	protected boolean isFinished() {
		return true;
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
		Robot.drivetrain.setspeed(0, 0);
	}
}
