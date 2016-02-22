package org.usfirst.frc.team3189.robot;

public class Constants {

	public static int potMin = 500;
	public static int potMax = 900;
	public static double potRange = 0.05;
	public static double elevatorLiftSpeed = 0.4;
	public static double elevatorLowerSpeed = 0.4;
	public static final double GOAL_HEIGHT = 85;
	public static final double GOAL_RANGE = 6;
	public static final double MAX_SPEED = 264;
	private static final double GRAVITY_INCHES = 32.18504;

	/**
	 * gets the best distance for a shoot based on the angle provided
	 * 
	 * @param angle
	 */
	public static double getDistanceFromAngle(double angle, double inchesPerSecond) {
		double x = -1;
		for (double t = 0.1; t < 1.446; t += .1) {
			double y = (MAX_SPEED * t * Math.sin(angle) - (GRAVITY_INCHES * t * t) / 2) 
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
}
