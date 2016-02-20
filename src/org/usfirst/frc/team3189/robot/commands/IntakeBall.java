package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * a {@link Command} used to attempt to intake a boulder from the field. runs
 * for 2 seconds.
 * 
 * @author Nate
 */
public class IntakeBall extends Command {

	public IntakeBall() {
		requires(Robot.shooter);
	}

	@Override
	protected void initialize() {
		Robot.shooter.setShooter(-0.25);
		setTimeout(2);
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		Robot.shooter.setShooter(0);
	}

	@Override
	protected void interrupted() {
		Robot.shooter.setShooter(0);
	}
}
