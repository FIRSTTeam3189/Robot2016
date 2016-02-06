package org.usfirst.frc.team3189.robot.utils;

import java.sql.Time;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PingSonar extends Thread{
	
	private SonarState state;
	private ReusableDigitalInput input;
	private ReusableDigitalOutput output;
	private double distance = -1;
	
	public PingSonar(int inChannel, int outChannel){
		input = new ReusableDigitalInput(0);
		input.free();
		output = new ReusableDigitalOutput(0);
		output.free();
		state = SonarState.Stoped;
	}
	
	@Override
	public void run() {
		state = SonarState.Ping;
		distance = 0;
		SmartDashboard.putNumber("test", 1);
		while (state != SonarState.Stoped){
			SmartDashboard.putNumber("test", 1);
			output.unfree();
			output.pulse(0.000001f);
			output.free();
			input.unfree();
			double starttime = Timer.getFPGATimestamp();
			while (input.get() == false || Timer.getFPGATimestamp() > starttime + 1){}
			starttime = Timer.getFPGATimestamp();
			while (input.get() == true || Timer.getFPGATimestamp() > starttime + 1){}
			double pingtime = Timer.getFPGATimestamp() - starttime;
			SmartDashboard.putNumber("test", 3);
			distance = pingtime * 3000 * 2.54;//6600;
			input.free();
			Timer.delay(0.5);
		}
	}
	
	public void stopIt(){
		state = SonarState.Stoped;
	}
	
	public double getDistance(){
		return distance;
	}
	
	public enum SonarState{
		Stoped,
		Ping,
		Pinged
	}
}
