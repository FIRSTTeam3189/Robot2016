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
	public static double DEAD_ZONE = 0.09;

	public static double ELEVATOR_FAST_LIFT_SPEED = 1.0;
	public static double ELEVATOR_FAST_LOWER_SPEED = 0.55;
	public static double ELEVATOR_SLOW_LIFT_SPEED = 0.40;
	public static double ELEVATOR_SLOW_LOWER_SPEED = 0.15;
	public static double ELEVATOR_HIGHEST_ANGLE = 70.3;
	public static double ELEVATOR_LOWEST_ANGLE = -22.5;

	public static double POT_UPPER = 83;
	public static double POT_LOWER = 900;
	public static double POT_RANGE = 2;
	public static double POT_SPAN = Math.abs(POT_UPPER - POT_LOWER);
	public static double ANGLE_SPAN = Math.abs(ELEVATOR_HIGHEST_ANGLE
			- ELEVATOR_LOWEST_ANGLE);

	public static double GOAL_HEIGHT = 100;
	public static double GOAL_RANGE = 2;
	public static double MAX_SPEED = 506;
	public static double GRAVITY_INCHES = 32.18504;
	
	public static double VISION_CLOSE = 20;
	public static double VISION_CLOSE_CENTER_X = 0.45;
	public static double VISION_CLOSE_CENTER_Y = 0.55;
	public static double VISION_CLOSE_CENTER_HEIGHT = 0.15;
	public static double VISION_CLOSE_CENTER_WIDTH = 0.15;
	public static double VISION_CLOSE_PERIMETER = 0.60;
	public static double VISION_CLOSE_ANGLE = 60;
	public static double VISION_FAR = 60;
	public static double VISION_FAR_CENTER_X = 0.55;
	public static double VISION_FAR_CENTER_Y = 0.45;
	public static double VISION_FAR_CENTER_HEIGHT = 0.1;
	public static double VISION_FAR_CENTER_WIDTH = 0.1;
	public static double VISION_FAR_PERIMETER = 0.4;
	public static double VISION_FAR_ANGLE = 45;
	

	public static int CAM_WIDTH = 320;
	public static int CAM_HEIGHT = 240;
	public static int CAM_EXPOSURE = 3;
	public static int CAM_BRIGHTNESS = 0;
	public static int CAM_FRAMES_PER_SECOND = 15;
	
	public static double AUTO_TURN_SPEED = 0.3;
	public static double AUTO_TURN_TIME = 1;
	public static double AUTO_FORWARD_TIME = 4;
	public static double AUTO_FORWARD_SPEED = .4;
	public static double AUTO_ANGLE = -10;
	
	public static double X_DEADZONE = 0.025;
	public static double Y_DEADZONE = 0.025;
	public static double DESIRED_X = 50;
	public static double DESIRED_Y = 50;

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
	
	public static void calcPotInfo(){
		POT_SPAN = Math.abs(POT_UPPER - POT_LOWER);
		ANGLE_SPAN = Math.abs(ELEVATOR_HIGHEST_ANGLE - ELEVATOR_LOWEST_ANGLE);
	}

	public static void initStatus() {
		calcPotInfo();
		SmartDashboard.putNumber("AutoForwardTime", AUTO_FORWARD_TIME);
		SmartDashboard.putNumber("AutoForwardSpeed", AUTO_FORWARD_SPEED);
		SmartDashboard.putNumber("AutoAngle", AUTO_ANGLE);
		SmartDashboard.putNumber("AutoTurnSpeed", AUTO_TURN_SPEED);
		SmartDashboard.putNumber("AutoTurnTime", AUTO_TURN_TIME);
		SmartDashboard.putNumber("Exposure", CAM_EXPOSURE);
		SmartDashboard.putNumber("Brightness", CAM_BRIGHTNESS);
		SmartDashboard.putNumber("ElevatorHighestAngle", ELEVATOR_HIGHEST_ANGLE);
		SmartDashboard.putNumber("ElevatorLowestAngle", ELEVATOR_LOWEST_ANGLE);
		SmartDashboard.putNumber("PotLower", POT_LOWER);
		SmartDashboard.putNumber("PotUpper", POT_UPPER);
	}

	public static void updateStatus() {
		AUTO_FORWARD_SPEED = SmartDashboard.getNumber("AutoForwardSpeed", AUTO_FORWARD_SPEED);
		AUTO_FORWARD_TIME = SmartDashboard.getNumber("AutoForwardTime", AUTO_FORWARD_TIME);
		AUTO_ANGLE = SmartDashboard.getNumber("AutoAngle", AUTO_ANGLE);
		AUTO_TURN_SPEED = SmartDashboard.getNumber("AutoTurnSpeed", AUTO_TURN_SPEED);
		AUTO_TURN_TIME = SmartDashboard.getNumber("AutoTurnTime", AUTO_TURN_TIME);
		CAM_EXPOSURE = (int) SmartDashboard.getNumber("Exposure");
		CAM_BRIGHTNESS = (int) SmartDashboard.getNumber("Brightness");
		ELEVATOR_HIGHEST_ANGLE = SmartDashboard.getNumber("ElevatorHighestAngle", ELEVATOR_HIGHEST_ANGLE);
		ELEVATOR_LOWEST_ANGLE = SmartDashboard.getNumber("ElevatorLowestAngle", ELEVATOR_LOWEST_ANGLE);
		POT_LOWER = SmartDashboard.getNumber("PotLower", POT_LOWER);
		POT_UPPER = SmartDashboard.getNumber("PotUpper", POT_UPPER);
	}
	
	public static double getPredictedCenterX(double angle){
		double spanx = VISION_FAR_CENTER_X - VISION_CLOSE_CENTER_X;
		double spana = VISION_FAR_ANGLE - VISION_CLOSE_ANGLE;
		return (((angle - VISION_CLOSE_ANGLE)/spana) * spanx) + VISION_CLOSE_CENTER_X;
	}
	
	public static double getPredictedCenterY(double angle){
		double spany = VISION_FAR_CENTER_Y - VISION_CLOSE_CENTER_Y;
		double spana = VISION_FAR_ANGLE - VISION_CLOSE_ANGLE;
		return (((angle - VISION_CLOSE_ANGLE)/spana) * spany) + VISION_CLOSE_CENTER_Y;
	}
	
	public static double getPredictedCenterHeight(double angle){
		double spanheight = VISION_CLOSE_CENTER_HEIGHT - VISION_FAR_CENTER_HEIGHT;
		double spana = VISION_CLOSE_ANGLE - VISION_FAR_ANGLE;
		return (((angle - VISION_CLOSE_ANGLE)/spana) * spanheight) + VISION_FAR_CENTER_HEIGHT;
	}
	
	public static double getPredictedCenterWidth(double angle){
		double spanwidth = VISION_CLOSE_CENTER_WIDTH - VISION_FAR_CENTER_WIDTH;
		double spana = VISION_CLOSE_ANGLE - VISION_FAR_ANGLE;
		return (((angle - VISION_CLOSE_ANGLE)/spana) * spanwidth) + VISION_FAR_CENTER_WIDTH;
	}
	
	public static double getPredictedCenterPerimeter(double angle){
		double spanperimeter = VISION_CLOSE_PERIMETER - VISION_FAR_PERIMETER;
		double spana = VISION_CLOSE_ANGLE - VISION_FAR_ANGLE;
		return (((angle - VISION_CLOSE_ANGLE)/spana) * spanperimeter) + VISION_FAR_PERIMETER;
	}
	
	public static double loadProp(Properties prop, String name, double defualt){
		String asdf = prop.getProperty(name);
		if(asdf == null){
			return defualt;
		}else{
			return Double.parseDouble(asdf);
		}
	}
	
	public static int loadProp(Properties prop, String name, int defualt){
		String asdf = prop.getProperty(name);
		if(asdf == null){
			return defualt;
		}else{
			return Integer.parseInt(asdf);
		}
	}

	public static void loadConfig() {
		Properties properties = new Properties();
		try {
			FileInputStream fis = new FileInputStream(robofigPath);
			properties.load(fis);
			AUTO_FORWARD_TIME = loadProp(properties, "AUTO_FORWARD_TIME", AUTO_FORWARD_TIME);
			AUTO_FORWARD_SPEED = loadProp(properties, "AUTO_FORWARD_SPEED", AUTO_FORWARD_SPEED);
			AUTO_ANGLE = loadProp(properties, "AUTO_ANGLE", AUTO_ANGLE);
			AUTO_TURN_SPEED = loadProp(properties, "AUTO_TURN_SPEED", AUTO_TURN_SPEED);
			AUTO_TURN_TIME = loadProp(properties, "AUTO_TURN_TIME", AUTO_TURN_TIME);
			CAM_BRIGHTNESS = loadProp(properties, "CAM_BRIGHTNESS", CAM_BRIGHTNESS);
			CAM_EXPOSURE = loadProp(properties, "CAM_EXPOSURE", CAM_EXPOSURE);
			ELEVATOR_HIGHEST_ANGLE = loadProp(properties, "ELEVATOR_HIGHEST_ANGLE", ELEVATOR_HIGHEST_ANGLE);
			ELEVATOR_LOWEST_ANGLE = loadProp(properties, "ELEVATOR_LOWEST_ANGLE", ELEVATOR_LOWEST_ANGLE);
			POT_LOWER = loadProp(properties, "POT_LOWER", POT_LOWER);
			POT_UPPER = loadProp(properties, "POT_UPPER", POT_UPPER);
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
			properties.setProperty("AUTO_FORWARD_TIME", String.valueOf(AUTO_FORWARD_TIME));
			properties.setProperty("AUTO_FORWARD_SPEED", String.valueOf(AUTO_FORWARD_SPEED));
			properties.setProperty("AUTO_ANGLE", String.valueOf(AUTO_ANGLE));
			properties.setProperty("AUTO_TURN_SPEED", String.valueOf(AUTO_TURN_SPEED));
			properties.setProperty("AUTO_TURN_TIME", String.valueOf(AUTO_TURN_TIME));
			properties.setProperty("CAM_BRIGHTNESS", String.valueOf(CAM_BRIGHTNESS));
			properties.setProperty("CAM_EXPOSURE", String.valueOf(CAM_EXPOSURE));
			properties.setProperty("ELEVATOR_HIGHEST_ANGLE", String.valueOf(ELEVATOR_HIGHEST_ANGLE));
			properties.setProperty("ELEVATOR_LOWEST_ANGLE", String.valueOf(ELEVATOR_LOWEST_ANGLE));
			properties.setProperty("POT_LOWER", String.valueOf(POT_LOWER));
			properties.setProperty("POT_UPPER", String.valueOf(POT_UPPER));
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
