package org.usfirst.frc.team3189.robot;

/**
 * map of the ports for the motors
 * @author Alex Nate Mitch
 *
 */

public class RobotMap {
	// PWM's
	public final static int rightbackMotor = 2;
	public final static int rightfrontMotor = 3;
	public final static int leftbackMotor = 0;
	public final static int leftfrontMotor = 1;
	
	public final static int leftShooterTalon = 0;
	public final static int rightShooterTalon = 1;
	
	public final static int WindowMotor = 8;
	
	// OI
	public final static int rightJoystick = 1;
	public final static int leftJoystick = 0;
	public final static int shooterJoystick = 2;
	
	// Pnumatics
	public final static int shooterExtend = 4; 
	public final static int shooterRetract = 5;
	
	public final static int gearboxLeftExtend = 0;
	public final static int gearboxRightExtend = 1;
	public final static int gearboxLeftRetract = 2;
	public final static int gearboxRightRetract = 3;
	
	//DIOs
	public final static int sonarFront = 0;
	public final static int sonarRight = 1;
	public final static int sonarBack = 2;
	public final static int sonarLeft = 3;
	public final static int ballSwitch = 4;
}
