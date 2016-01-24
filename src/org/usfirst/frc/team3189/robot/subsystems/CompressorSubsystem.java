package org.usfirst.frc.team3189.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CompressorSubsystem extends Subsystem {
	private Compressor compressor = new Compressor();
	
	public void runCompressor(){
		if(!compressor.enabled()&& compressor.getPressureSwitchValue()){
			compressor.start();
		}
		else if(compressor.enabled() && !compressor.getPressureSwitchValue()){
			compressor.stop();
		}
	}
    public void updateStatus(){
    	SmartDashboard.putBoolean("pressure switch", compressor.getPressureSwitchValue());
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

