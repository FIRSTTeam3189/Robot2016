package org.usfirst.frc.team3189.robot;

import com.ni.vision.NIVision.Range;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants {
	public static int redLow = 75;
	public static int redHigh = 150;
	public static int greenLow = 200;
	public static int greenHigh = 255;
	public static int blueLow = 200;
	public static int blueHigh = 255;

	public static Range getRedRange() {
		return new Range(redLow, redHigh);
	}

	public static Range getGreenRange() {
		return new Range(greenLow, greenHigh);
	}

	public static Range getBlueRange() {
		return new Range(blueLow, blueHigh);
	}
	
	public static void initStatus(){
		SmartDashboard.putNumber("RedLow", redLow);
		SmartDashboard.putNumber("RedHigh", redHigh);
		SmartDashboard.putNumber("GreenHigh", greenHigh);
		SmartDashboard.putNumber("GreenLow", greenLow);
		SmartDashboard.putNumber("BlueHigh", blueHigh);
		SmartDashboard.putNumber("BlueLow", blueLow);
	}
	
	public static void updateStatus(){
		redHigh = (int) SmartDashboard.getNumber("RedHigh", redHigh);
		redLow = (int) SmartDashboard.getNumber("RedLow", redLow);
		greenHigh = (int) SmartDashboard.getNumber("GreenHigh", greenHigh);
		greenLow = (int) SmartDashboard.getNumber("GreenLow", greenLow);
		blueHigh = (int) SmartDashboard.getNumber("BlueHigh", blueHigh);
		blueLow = (int) SmartDashboard.getNumber("BlueLow", blueLow);
	}
}
