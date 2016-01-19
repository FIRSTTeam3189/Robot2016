package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.utils.Piston;

import edu.wpi.first.wpilibj.command.Subsystem;
/**
 * holds the interface for the shooter piston
 * @author Mitch
 *
 */
public class Kicker extends Subsystem {
    
	private Piston shooterPiston = new Piston(RobotMap.shooterRetract, RobotMap.shooterExtend);

/**
 * sets the method for retracting and extending the piston
 */
	public void retract(){
		shooterPiston.retractPiston();
	}
	public void extend(){
		shooterPiston.extendPiston();
	}
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

