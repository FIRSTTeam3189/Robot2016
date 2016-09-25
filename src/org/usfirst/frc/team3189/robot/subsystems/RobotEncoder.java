package org.usfirst.frc.team3189.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class RobotEncoder extends Subsystem {
    edu.wpi.first.wpilibj.Encoder encoder = new edu.wpi.first.wpilibj.Encoder(0, 2);
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public int getEncoderValue(){
    	return encoder.get();
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

