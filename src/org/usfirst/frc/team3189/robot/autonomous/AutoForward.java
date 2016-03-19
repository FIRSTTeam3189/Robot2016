package org.usfirst.frc.team3189.robot.autonomous;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoForward extends CommandGroup {
	
    
    public  AutoForward() {
        addSequential(new DriveForwardCommand(0));
    }
}
