package org.usfirst.frc.team3189.robot;

import org.usfirst.frc.team3189.robot.commands.IntakeBall;
import org.usfirst.frc.team3189.robot.commands.ShootBallCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;


public class OI {
	private Joystick rightJoystick = new Joystick(RobotMap.rightJoystick);
	private Joystick leftJoystick = new Joystick(RobotMap.leftJoystick);
	private JoystickButton shootBall = new JoystickButton(rightJoystick, 1);
	private JoystickButton intakeBall = new JoystickButton(rightJoystick, 11);
	
	
	public double getLeftJoystickY(){
		return leftJoystick.getY();
	}
	public double getRightJoystickY(){
		return rightJoystick.getY();
	}
	public OI(){
		shootBall.whenPressed(new ShootBallCommand());
		intakeBall.whileHeld(new IntakeBall());
	}
	
   
}

