package org.usfirst.frc.team3189.robot.autonomous;

import edu.wpi.first.wpilibj.Timer;

import org.usfirst.frc.team3189.robot.Constants;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class TeachingAutoIgnore extends CommandGroup {

    public TeachingAutoIgnore() {
       addSequential(new DriveForwardCommand(Constants.AUTO_FORWARD_TIME));
       Timer.delay(1);
       addSequential(new ReverseDirectionCommand(3));
       Timer.delay(1);
       addSequential(new ReverseDirectionCommand(3));
       Timer.delay(1);
       addSequential(new TurnLeft(60));
       Timer.delay(1);
       addSequential(new TurnRight(60));
       Timer.delay(1);
       addSequential(new AutonomousShooter(3));
    }

}
