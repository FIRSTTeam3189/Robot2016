package org.usfirst.frc.team3189.robot.autonomous;

import org.usfirst.frc.team3189.robot.commands.PotGoTo;
import org.usfirst.frc.team3189.robot.commands.ShootBallCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoFire extends CommandGroup {

	public AutoFire() {
		addSequential(new AutoForwardSonar());
		addSequential(new TurnRight(1));
		addSequential(new PotGoTo(0.45));
		// call vision method here
		addSequential(new ShootBallCommand(0.7));
	}
}
