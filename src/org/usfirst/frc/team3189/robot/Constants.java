package org.usfirst.frc.team3189.robot;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants {

	public static int POT_MIN = 500;
	public static int POT_MAX = 900;
	public static double POT_RANGE = 0.05;
	public static double ELEVATOR_LIFT_SPEED = 0.4;
	public static double ELEVATOR_LOWER_SPEED = 0.4;
	
	public static double GOAL_HEIGHT = 85;
	public static double GOAL_RANGE = 6;
	public static double MAX_SPEED = 264;
	public static double GRAVITY_INCHES = 32.18504;
	
	public static int CAM_WIDTH = 640;
	public static int CAM_HEIGHT = 480;
	public static int POINTS_PER_DEGREE = 20;
	public static int POT_VALUE_AT_ZERO = 100;
	
	public static double DEAD_ZONE = 0.05;

	/**
	 * gets the best distance for a shoot based on the angle provided
	 * 
	 * @param angle
	 */
	public static double getDistanceFromAngle(double angle, double inchesPerSecond) {
		double x = -1;
		for (double t = 0.1; t < 1.446; t += .1) {
			double y = (MAX_SPEED/12 * t * Math.sin(angle) - (GRAVITY_INCHES * t * t) / 2) 
					+ getShootHeight(angle);
			if(y >= GOAL_HEIGHT - GOAL_RANGE && y <= GOAL_HEIGHT + GOAL_RANGE){
				x = MAX_SPEED * t * Math.cos(angle);
				break;
			}
		}
		return x;
	}

	/**
	 * gets the height of the shooting point based on the angle provided
	 * @param angle of the shooter
	 * @return the height of the shooting point
	 */
	public static double getShootHeight(double angle) {
		return 12;// TODO make this return a height of shooter based on prvided
					// angle.
	}

	public static void initStatus() {
	}

	public static void updateStatus() {

	}
	
	public static void loadConfig(){
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream("path/filename"));
			CAM_HEIGHT = Integer.parseInt(properties.getProperty("CAM_HEIGHT"));
			CAM_WIDTH = Integer.parseInt(properties.getProperty("CAM_WIDTH"));
			DEAD_ZONE = Integer.parseInt(properties.getProperty("DEAD_ZONE"));
			ELEVATOR_LIFT_SPEED = Integer.parseInt(properties.getProperty("ELEVATOR_LIFT_SPEED"));
			ELEVATOR_LOWER_SPEED = Integer.parseInt(properties.getProperty("ELEVATOR_LOWER_SPEED"));
			GOAL_HEIGHT = Integer.parseInt(properties.getProperty("GOAL_HEIGHT"));
			GOAL_RANGE = Integer.parseInt(properties.getProperty("GOAL_RANGE"));
			GRAVITY_INCHES = Integer.parseInt(properties.getProperty("GRAVITY_INCHES"));
			MAX_SPEED = Integer.parseInt(properties.getProperty("MAX_SPEED"));
			POINTS_PER_DEGREE = Integer.parseInt(properties.getProperty("POINTS_PER_DEGREE"));
			POT_MAX = Integer.parseInt(properties.getProperty("POT_MAX"));
			POT_MIN = Integer.parseInt(properties.getProperty("POT_MIN"));
			POT_RANGE = Integer.parseInt(properties.getProperty("POT_RANGE"));
			POT_VALUE_AT_ZERO = Integer.parseInt(properties.getProperty("POT_VALUE_AT_ZERO"));
		} catch (IOException e) {
			SmartDashboard.putString("Message", "could not load cofiguration.");
		}
	}
	
	public static void saveConfig(){
		Properties properties = new Properties();
		try {
			properties.setProperty("CAM_HEIGHT", String.valueOf(CAM_HEIGHT));
			properties.setProperty("CAM_WIDTH", String.valueOf(CAM_WIDTH));
			properties.setProperty("DEAD_ZONE", String.valueOf(DEAD_ZONE));
			properties.setProperty("ELEVATOR_LIFT_SPEED", String.valueOf(ELEVATOR_LIFT_SPEED));
			properties.setProperty("ELEVATOR_LOWER_SPEED", String.valueOf(ELEVATOR_LOWER_SPEED));
			properties.setProperty("GOAL_HEIGHT", String.valueOf(GOAL_HEIGHT));
			properties.setProperty("GOAL_RANGE", String.valueOf(GOAL_RANGE));
			properties.setProperty("GRAVITY_INCHES", String.valueOf(GRAVITY_INCHES));
			properties.setProperty("MAX_SPEED", String.valueOf(MAX_SPEED));
			properties.setProperty("POINTS_PER_DEGREE", String.valueOf(POINTS_PER_DEGREE));
			properties.setProperty("POT_MAX", String.valueOf(POT_MAX));
			properties.setProperty("POT_MIN", String.valueOf(POT_MIN));
			properties.setProperty("POT_RANGE", String.valueOf(POT_RANGE));
			properties.setProperty("POT_VALUE_AT_ZERO", String.valueOf(POT_VALUE_AT_ZERO));
			properties.store(new FileOutputStream("path/filename"), "ROBOT PROPERTIES");
		} catch (IOException e) {
			SmartDashboard.putString("Message", "could not load cofiguration.");
		}
	}
}
