package org.usfirst.frc.team3189.robot.utils;

import java.sql.Time;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class PingSonar implements Runnable{
	
	private SonarState state;
	private ReusableDigitalInput input;
	private ReusableDigitalOutput output;
	private double distance = -1;
	
	public PingSonar(int channel){
		input = new ReusableDigitalInput(channel);
		input.free();
		output = new ReusableDigitalOutput(channel);
		output.free();
		state = SonarState.Stoped;
	}
	
	public void start(){
		Thread thread = new Thread(this);
		thread.start();
	}
	
	
	public void run() {
		state = SonarState.Ping;
		distance = 0;
		while (state != SonarState.Stoped){
			output.unfree();
			output.pulse(0.000001f);
			output.free();
			input.unfree();
			double starttime = Timer.getFPGATimestamp();
			try{
				while (input.get() == false){
					if(Timer.getFPGATimestamp() > starttime + 0.1){
						throw new Exception("Time Out");
					}
				}
				starttime = Timer.getFPGATimestamp();
				while (input.get() == true){
					if(Timer.getFPGATimestamp() > starttime + 0.1){
						throw new Exception("Time Out");
					}
				}
				distance = Timer.getFPGATimestamp() - starttime;
			}catch(Exception e){
				
			}
			input.free();
			Timer.delay(0.25);
		}
	}
	
	public double getInches(){
		return distance * 2598 * 2.54;
	}
	
	public double getCenimeters(){
		return distance * 2598;
	}
	
	public void stop(){
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
