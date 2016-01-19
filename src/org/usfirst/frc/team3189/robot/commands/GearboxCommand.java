package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;
import org.usfirst.frc.team3189.robot.subsystems.Gearbox;
import org.usfirst.frc.team3189.robot.utils.Piston;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Switches the state of the {@link Gearbox}'s {@link Piston}.
 * 
 * @author Alex
 */
public class GearboxCommand extends Command {
	public GearboxCommand() {
		requires(Robot.gearbox);
	}

	@Override
	protected void initialize() {
		Robot.gearbox.togglePistonState();
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
	}
}
