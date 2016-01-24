package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;
import org.usfirst.frc.team3189.robot.subsystems.Kicker;
import org.usfirst.frc.team3189.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.command.Command;

/**
 * a {@link Command} the shoot the boulder using the {@link Shooter} and
 * {@link Kicker}.
 * 
 * @author Nate, alex, mitch
 */
public class ShootBallCommand extends Command {

	public ShootBallCommand() {
		requires(Robot.Shooter);
		
	}

	@Override
	protected void initialize() {
		Robot.Shooter.setShooter(1);
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
		Robot.Shooter.setShooter(0);
	}

	@Override
	protected void interrupted() {
		Robot.Shooter.setShooter(0);
	}
}
