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
	private long begTime;

	public ShootBallCommand() {
		requires(Robot.Shooter);
		requires(Robot.Kicker);
	}

	@Override
	protected void initialize() {
		setTimeout(3);
		begTime = System.currentTimeMillis();

	}

	@Override
	protected void execute() {
		if (begTime + 2000 > System.currentTimeMillis()) {
			Robot.Shooter
					.setShooter((System.currentTimeMillis() - begTime) / 2000);

		} else if (Robot.Kicker.isRetracted()
				&& begTime + 2000 <= System.currentTimeMillis()) {
			Robot.Shooter.setShooter(1);
			Robot.Kicker.extend();

		}
		
	}

	@Override
	protected boolean isFinished() {
		return isTimedOut();
	}

	@Override
	protected void end() {
		Robot.Shooter.setShooter(0);
		Robot.Kicker.retract();
	}

	@Override
	protected void interrupted() {
		Robot.Shooter.setShooter(0);
		Robot.Kicker.retract();
	}
}
