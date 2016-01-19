package org.usfirst.frc.team3189.robot;

import org.usfirst.frc.team3189.robot.commands.IntakeBall;
import org.usfirst.frc.team3189.robot.commands.ShootBallCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

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
	/** the button used to start {@link ShootBallCommand} */
	private JoystickButton shootBall = new JoystickButton(rightJoystick, 1);
	/** the button used to start {@link IntakeBall} */
	private JoystickButton intakeBall = new JoystickButton(rightJoystick, 11);

	/**
	 * creates a new {@link OI}.
	 */
	public OI() {
		shootBall.whenPressed(new ShootBallCommand());
		intakeBall.whileHeld(new IntakeBall());
	}

	/**
	 * used to get the leftJoysticks Y axis value
	 * 
	 * @return the position of the Y axis of the left {@link Joystick}. a value
	 *         bewteen -1 and 1, negitive beening forward from the initial state
	 *         and positive values being behind the initial state.
	 */
	public double getLeftJoystickY() {
		return leftJoystick.getY();
	}

	/**
	 * used to get the rightJoysticks Y axis value
	 * 
	 * @return the position of the Y axis of the right {@link Joystick}. a value
	 *         bewteen -1 and 1, negitive beening forward from the initial state
	 *         and positive values being behind the initial state.
	 */
	public double getRightJoystickY() {
		return rightJoystick.getY();
	}
}
