package org.usfirst.frc.team3189.robot.autonomous;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.Robot;
import org.usfirst.frc.team3189.robot.commands.PotGoTo;
import org.usfirst.frc.team3189.robot.commands.SaveKinectImages;
import org.usfirst.frc.team3189.robot.commands.ShootBallPredicted;
import org.usfirst.frc.team3189.robot.commands.VisionAim;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoForwardLowBar extends CommandGroup {
    
    public  AutoForwardLowBar() {
    	long start = System.currentTimeMillis();
    	addSequential(new PotGoTo(Constants.AUTO_ANGLE));
        addSequential(new DriveForwardCommand(Constants.AUTO_FORWARD_TIME*0.60));
        
        addSequential(new TurnRight(Constants.AUTO_TURN_TIME));
        addSequential(new PotGoTo(40));
        //addSequential(new VisionAim( 12 - ((System.currentTimeMillis() - start)/1000)));
        //addSequential(new ShootBallPredicted());
        addParallel(new SaveKinectImages());
        addSequential(new DriveForwardCommand(Constants.AUTO_FORWARD_TIME*0.40));
        
    }
}
