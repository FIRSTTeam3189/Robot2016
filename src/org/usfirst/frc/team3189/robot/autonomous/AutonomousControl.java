package org.usfirst.frc.team3189.robot.autonomous;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousControl extends CommandGroup {

	public AutonomousControl() {
		addSequential(new DriveForwardCommand(3));
		Timer.delay(1);
		addSequential(new ReverseDirectionCommand(3));
		Timer.delay(1);
		addSequential(new TurnLeft(3));
		Timer.delay(1);
		addSequential(new TurnRight(3));
		Timer.delay(1);
		addSequential(new AutonomousShooter(3));
	}
}
