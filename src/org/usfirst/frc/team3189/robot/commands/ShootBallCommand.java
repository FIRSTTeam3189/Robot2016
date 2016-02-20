package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;
import org.usfirst.frc.team3189.robot.subsystems.Kicker;

import edu.wpi.first.wpilibj.command.Command;

/**
 * a {@link Command} the shoot the boulder using the {@link Shooter} and
 * {@link Kicker}.
 * 
 * @author Nate, alex, mitch
 */
public class ShootBallCommand extends Command {
	private long begTime;

	public ShootBallCommand() {
		requires(Robot.shooter);
		requires(Robot.kicker);
	}

	@Override
	protected void initialize() {
		setTimeout(3);
		begTime = System.currentTimeMillis();

	}

	@Override
	protected void execute() {
		if (begTime + 2000 > System.currentTimeMillis()) {
			Robot.shooter
					.setShooter((System.currentTimeMillis() - begTime) / 2000);

		} else if (Robot.kicker.isRetracted()
				&& begTime + 2000 <= System.currentTimeMillis()) {
			Robot.shooter.setShooter(1);
			Robot.kicker.extend();

		}
		
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		Robot.shooter.setShooter(0);
		Robot.kicker.retract();
	}

	@Override
	protected void interrupted() {
		Robot.shooter.setShooter(0);
		Robot.kicker.retract();
	}
}
