package org.usfirst.frc.team3189.robot.autonomous;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.commands.PotGoTo;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoForwardLowBar extends CommandGroup {
    
    public  AutoForwardLowBar() {
    	addSequential(new PotGoTo(Constants.AUTO_ANGLE));
        addSequential(new DriveForwardCommand(0));
    }
}
