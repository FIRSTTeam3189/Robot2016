package org.usfirst.frc.team3189.robot;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.usfirst.frc.team3189.robot.commands.VisionAim;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants {
	
	private static String robofigPath = "/home/lvuser/robofig.conf";

	public static double POT_UPPER = 83;
	public static double POT_LOWER = 900;
	public static double POT_RANGE = 2;
	public static double ELEVATOR_LIFT_SPEED = 1.0;
	public static double ELEVATOR_LOWER_SPEED = 0.55;
	public static double SLOW_ELEVATOR_LIFT_SPEED = 0.60;
	public static double SLOW_ELEATOR_LOWER_SPEED = 0.25;
	public static double ELEVATOR_HIGHEST_ANGLE = 70.3;
	public static double ELEVATOR_LOWEST_ANGLE = -22.5;

	public static double X_DEADZONE = 0.025;
	public static double Y_DEADZONE = 0.025;
	public static double DESIRED_X = 50;
	public static double DESIRED_Y = 50;

	public static double AUTO_TURN_SPEED = 0.3;

	// constants for auto fire
	public static double TIME_MOVE_FORWARD = 4.0;

	public static double POT_SPAN = Math.abs(POT_UPPER - POT_LOWER);
	public static double ANGLE_SPAN = Math.abs(ELEVATOR_HIGHEST_ANGLE
			- ELEVATOR_LOWEST_ANGLE);

	public static double GOAL_HEIGHT = 100;
	public static double GOAL_RANGE = 2;
	public static double MAX_SPEED = 506;
	public static double GRAVITY_INCHES = 32.18504;

	public static int CAM_WIDTH = 640;
	public static int CAM_HEIGHT = 480;

	public static double DEAD_ZONE = 0.09;

	public static double AutoForwardTime = 4;
	public static double AutoForwardSpeed = .4;
	public static double AutoAngle = -10;

	/**
	 * gets the best distance for a shoot based on the angle provided
	 * 
	 * @param angle
	 */
	public static double getDistanceFromAngle(double angle,
			double inchesPerSecond) {
		double x = -1;
		for (double t = 0.01; t < 3; t += .01) {
			double y = (MAX_SPEED * t * Math.sin(Math.toRadians(angle)) - (GRAVITY_INCHES
					* t * t) / 2)
					+ getShootHeight(angle);
			if (y >= GOAL_HEIGHT - GOAL_RANGE && y <= GOAL_HEIGHT + GOAL_RANGE) {
				x = (MAX_SPEED) * t * Math.cos(Math.toRadians(angle));
				SmartDashboard.putNumber("YYYYYYY", y);
				SmartDashboard.putNumber("TIME", t);
				break;
			}
		}
		return x;
	}

	/**
	 * gets the height of the shooting point based on the angle provided
	 * 
	 * @param angle
	 *            of the shooter
	 * @return the height of the shooting point
	 */
	public static double getShootHeight(double angle) {
		return (15.75 * Math.sin(Math.toRadians(angle))) + 12.25;
	}

	public static void initStatus() {
		SmartDashboard.putNumber("AutoForwardTime", AutoForwardTime);
		SmartDashboard.putNumber("AutoForwardSpeed", AutoForwardSpeed);
		SmartDashboard.putNumber("AutoAngle", AutoAngle);
		SmartDashboard.putNumber("Exposure", 3);
		SmartDashboard.putNumber("Brightness", 0);
		SmartDashboard.putNumber("DEAD_ZONE", DEAD_ZONE);
	}

	public static void updateStatus() {
		AutoForwardSpeed = SmartDashboard.getNumber("AutoForwardSpeed");
		AutoForwardTime = SmartDashboard.getNumber("AutoForwardTime");
		AutoAngle = SmartDashboard.getNumber("AutoAngle");
		DEAD_ZONE = SmartDashboard.getNumber("DEAD_ZONE", DEAD_ZONE);
		Robot.cam.change((int) SmartDashboard.getNumber("Exposure"),
				(int) SmartDashboard.getNumber("Brightness"));
	}
	
	public static double loadDoubleProp(Properties prop, String name, double defualt){
		String asdf = prop.getProperty(name);
		if(asdf == null){
			return defualt;
		}else{
			return Double.parseDouble(asdf);
		}
	}

	public static void loadConfig() {
		Properties properties = new Properties();
		try {
			FileInputStream fis = new FileInputStream(robofigPath);
			properties.load(fis);
			DEAD_ZONE = loadDoubleProp(properties, "DEAD_ZONE", DEAD_ZONE);
			fis.close();
			SmartDashboard.putString("Message", "loaded cofiguration.");
		} catch (IOException e) {
			SmartDashboard.putString("Message", "could not load cofiguration." + e.toString());
		}
	}

	public static void saveConfig() {
		Properties properties = new Properties();
		try {
			properties.setProperty("DEAD_ZONE", String.valueOf(DEAD_ZONE));
			File file = new File(robofigPath);
			if(!file.exists()){
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file);
			properties.store(fos,
					"ROBOT PROPERTIES");
			fos.close();
			SmartDashboard.putString("Message", "saved cofiguration.");
		} catch (IOException e) {
			SmartDashboard.putString("Message", "could not save cofiguration. " + e.toString());
		}
	}
}
