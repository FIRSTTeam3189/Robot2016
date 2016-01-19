package org.usfirst.frc.team3189.robot;

import org.usfirst.frc.team3189.robot.commands.IntakeBall;
import org.usfirst.frc.team3189.robot.commands.ShootBallCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * overview interfae for the robot
 * @author Alex Nate Mitch
 *
 */
public class OI {
	private Joystick rightJoystick = new Joystick(RobotMap.rightJoystick);
	private Joystick leftJoystick = new Joystick(RobotMap.leftJoystick);
	private JoystickButton shootBall = new JoystickButton(rightJoystick, 1);
	private JoystickButton intakeBall = new JoystickButton(rightJoystick, 11);
	
/*
 * getters for left and right joysitck	
 */

	public double getLeftJoystickY(){
		return leftJoystick.getY();
	}
	public double getRightJoystickY(){
		return rightJoystick.getY();
	}
	
/*
 * makes it so you can shoot and intake more than 1 ball
 */
	public OI(){
		shootBall.whenPressed(new ShootBallCommand());
		intakeBall.whileHeld(new IntakeBall());
	}
	
   
}

