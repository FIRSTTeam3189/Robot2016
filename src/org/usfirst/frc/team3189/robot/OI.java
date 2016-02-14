package org.usfirst.frc.team3189.robot;

import org.usfirst.frc.team3189.robot.autonomous.AutonomousControl;
import org.usfirst.frc.team3189.robot.commands.DrivetrainReverse;
import org.usfirst.frc.team3189.robot.commands.ExtendLeftGearbox;
import org.usfirst.frc.team3189.robot.commands.ExtendRightGearbox;
import org.usfirst.frc.team3189.robot.commands.GearboxCommand;
import org.usfirst.frc.team3189.robot.commands.IntakeBall;
import org.usfirst.frc.team3189.robot.commands.RetractLeftGearbox;
import org.usfirst.frc.team3189.robot.commands.RetractRightGearbox;
import org.usfirst.frc.team3189.robot.commands.ShootBallCommand;
import org.usfirst.frc.team3189.robot.commands.SonarCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * a class to provide an interface to use the human interface devices used for
 * the 2016 team 3189 robot.
 * 
 * @author Alex, Nate, Mitch
 *
 */
public class OI {

	/**
	 * the {@link Joystick} using the {@link RobotMap}'s rightJoystick channel
	 */
	private Joystick rightJoystick = new Joystick(RobotMap.rightJoystick);
	/**
	 * the {@link Joystick} using the {@link RobotMap}'s leftJoystick channel
	 */
	private Joystick leftJoystick = new Joystick(RobotMap.leftJoystick);
	/** the {@link Joystick} using the {@link RobotMap}'s shooter channel */
	private Joystick shooterJoystick = new Joystick(RobotMap.shooterJoystick);
	/** the button used to start {@link ShootBallCommand} */
	private JoystickButton shootBall = new JoystickButton(shooterJoystick, 1);
	/** the button used to start {@link IntakeBall} */
	private JoystickButton intakeBall = new JoystickButton(leftJoystick, 11);
	private JoystickButton shiftButton = new JoystickButton(leftJoystick, 6);
	
	private JoystickButton extendLeftGearboxPiston = new JoystickButton(leftJoystick, 3);
	private JoystickButton retractLeftGearboxPiston = new JoystickButton(leftJoystick, 1);
	
	private JoystickButton extendRightGearboxPiston = new JoystickButton(rightJoystick, 1);
	private JoystickButton retractRightGearboxPiston = new JoystickButton(rightJoystick, 3);
	
	private JoystickButton reverseDirection = new JoystickButton(rightJoystick, 10);
	
	private JoystickButton autonomousControl = new JoystickButton(rightJoystick, 9);
	/**
	 * creates a new {@link OI}.
	 */
	public OI() {
		shootBall.whenPressed(new ShootBallCommand());
		//intakeBall.whileHeld(new IntakeBall());
		//shiftButton.whenPressed(new GearboxCommand());
		intakeBall.whenPressed(new SonarCommand(false));
		shiftButton.whenPressed(new SonarCommand(true));
		
		extendLeftGearboxPiston.whenPressed(new ExtendLeftGearbox());
		retractLeftGearboxPiston.whenPressed(new RetractLeftGearbox());
		
		extendRightGearboxPiston.whenPressed(new ExtendRightGearbox());
		retractRightGearboxPiston.whenPressed(new RetractRightGearbox());
		
		reverseDirection.whenPressed(new DrivetrainReverse());
		
		autonomousControl.whenPressed(new AutonomousControl());
	}

	/**
	 * used to get the leftJoysticks Y axis value
	 * 
	 * @return the position of the Y axis of the left {@link Joystick}. a value
	 *         between -1 and 1, negative being forward from the initial state
	 *         and positive values being behind the initial state.
	 */
	public double getLeftJoystickY() {
		return leftJoystick.getY();
	}
	public double getShooterJoystickY(){
		return shooterJoystick.getY();
	}
	/**
	 * used to get the rightJoysticks Y axis value
	 * 
	 * @return the position of the Y axis of the right {@link Joystick}. a value
	 *         between -1 and 1, negative being forward from the initial state
	 *         and positive values being behind the initial state.
	 */
	public double getRightJoystickY() {
		return rightJoystick.getY();
	}
	
	public void updateStatus(){
		SmartDashboard.putNumber("JoyY", getLeftJoystickY());
		SmartDashboard.putNumber("rightJoystick", getRightJoystickY());
		SmartDashboard.putNumber("JoyShooter", getShooterJoystickY());
		
	}
}
