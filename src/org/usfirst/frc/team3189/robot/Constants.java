package org.usfirst.frc.team3189.robot;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Constants {

	public static double POT_UPPER = 177;
	public static double POT_LOWER = 483;
	public static double POT_RANGE = 2;
	public static double ELEVATOR_LIFT_SPEED = 0.60;
	public static double ELEVATOR_LOWER_SPEED = 0.20;
	public static double ELEVATOR_HIGHEST_ANGLE = 63.1;
	public static double ELEVATOR_LOWEST_ANGLE = -22.6;
	
	public static double POT_SPAN = Math.abs(POT_UPPER - POT_LOWER);
	public static double ANGLE_SPAN = Math.abs(ELEVATOR_HIGHEST_ANGLE - ELEVATOR_LOWEST_ANGLE);
	
	public static double GOAL_HEIGHT = 100;
	public static double GOAL_RANGE = 2;
	public static double MAX_SPEED = 506;
	public static double GRAVITY_INCHES = 32.18504;
	
	public static int CAM_WIDTH = 640;
	public static int CAM_HEIGHT = 480;
	
	public static double DEAD_ZONE = 0.05;

	/**
	 * gets the best distance for a shoot based on the angle provided
	 * 
	 * @param angle
	 */
	public static double getDistanceFromAngle(double angle, double inchesPerSecond) {
		double x = -1;
		for (double t = 0.01; t < 3; t += .01) {
			double y = (MAX_SPEED * t * Math.sin(Math.toRadians(angle)) - (GRAVITY_INCHES * t * t) / 2) 
					+ getShootHeight(angle);
			if(y >= GOAL_HEIGHT - GOAL_RANGE && y <= GOAL_HEIGHT + GOAL_RANGE){
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
	 * @param angle of the shooter
	 * @return the height of the shooting point
	 */
	public static double getShootHeight(double angle) {
		return (15.75 * Math.sin(Math.toRadians(angle)))+12.25;
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
			POT_LOWER = Integer.parseInt(properties.getProperty("POT_MAX"));
			POT_UPPER = Integer.parseInt(properties.getProperty("POT_MIN"));
			POT_RANGE = Integer.parseInt(properties.getProperty("POT_RANGE"));
			ELEVATOR_LOWEST_ANGLE = Integer.parseInt(properties.getProperty("POT_VALUE_AT_ZERO"));
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
			properties.setProperty("POT_MAX", String.valueOf(POT_LOWER));
			properties.setProperty("POT_MIN", String.valueOf(POT_UPPER));
			properties.setProperty("POT_RANGE", String.valueOf(POT_RANGE));
			properties.setProperty("POT_VALUE_AT_ZERO", String.valueOf(ELEVATOR_LOWEST_ANGLE));
			properties.store(new FileOutputStream("path/filename"), "ROBOT PROPERTIES");
		} catch (IOException e) {
			SmartDashboard.putString("Message", "could not load cofiguration.");
		}
	}
}
