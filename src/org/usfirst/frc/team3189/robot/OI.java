package org.usfirst.frc.team3189.robot;

import org.usfirst.frc.team3189.robot.autonomous.AutonomousControl;
import org.usfirst.frc.team3189.robot.commands.DrivetrainReverse;
import org.usfirst.frc.team3189.robot.commands.ExtendLeftGearbox;
import org.usfirst.frc.team3189.robot.commands.ExtendRightGearbox;
import org.usfirst.frc.team3189.robot.commands.IntakeBall;
import org.usfirst.frc.team3189.robot.commands.Kick;
import org.usfirst.frc.team3189.robot.commands.PotGoTo;
import org.usfirst.frc.team3189.robot.commands.RetractLeftGearbox;
import org.usfirst.frc.team3189.robot.commands.RetractRightGearbox;
import org.usfirst.frc.team3189.robot.commands.ShootBallCommand;
import org.usfirst.frc.team3189.robot.commands.ShooterControll;
import org.usfirst.frc.team3189.robot.commands.ShooterControll2;
import org.usfirst.frc.team3189.robot.commands.SonarCommand;
import org.usfirst.frc.team3189.robot.commands.WindowMotorControlScetchy;
import org.usfirst.frc.team3189.robot.config_commands.ConfigSave;
import org.usfirst.frc.team3189.robot.config_commands.ElevatorConfig;

import edu.wpi.first.wpilibj.Joystick;
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
	private Joystick config = new Joystick(3);
	/** the button used to start {@link ShootBallCommand} */
	private JoystickButton shooterTrigger = new JoystickButton(shooterJoystick, 1);
	/** the button used to start {@link IntakeBall} */
	private JoystickButton left11 = new JoystickButton(leftJoystick, 11);
	private JoystickButton left6 = new JoystickButton(leftJoystick, 6);
	private JoystickButton shooter2 = new JoystickButton(shooterJoystick, 2);

	// private JoystickButton extendRightGearboxPiston = new
	// JoystickButton(rightJoystick, 1);
	// private JoystickButton retractRightGearboxPiston = new
	// JoystickButton(rightJoystick, 3);

	private JoystickButton right1 = new JoystickButton(rightJoystick, 1);

	private JoystickButton right9 = new JoystickButton(rightJoystick, 9);

	private JoystickButton shooter7 = new JoystickButton(shooterJoystick, 7);
	private JoystickButton shooter8 = new JoystickButton(shooterJoystick, 8);
	private JoystickButton shooter9 = new JoystickButton(shooterJoystick, 9);
	private JoystickButton shooter10 = new JoystickButton(shooterJoystick, 10);
	private JoystickButton shooter11 = new JoystickButton(shooterJoystick, 11);
	private JoystickButton shooter3 = new JoystickButton(shooterJoystick, 3);
	private JoystickButton shooter4 = new JoystickButton(shooterJoystick, 4);
	private JoystickButton shooter5 = new JoystickButton(shooterJoystick, 5);
	private JoystickButton shooter6 = new JoystickButton(shooterJoystick, 6);
	private JoystickButton config1 = new JoystickButton(config, 1);
	private JoystickButton config2 = new JoystickButton(config, 8);

	/**
	 * creates a new {@link OI}.
	 */
	public OI() {
		shooterTrigger.whenPressed(new ShootBallCommand(0.3));
		// left11.whenPressed(new IntakeBall());
		// shooter2.whileHeld(new PotFollow());
		right1.whenPressed(new DrivetrainReverse());
		// right9.whenPressed(new AutonomousControl());

		//shooter3.whenPressed(new IntakeBall());
		//shooter4.whenPressed(new ShooterControll());
		
		shooter7.whenPressed(new PotGoTo(-24));
		shooter6.whenPressed(new IntakeBall());
		
		shooter8.whenPressed(new PotGoTo(-10));
		shooter9.whenPressed(new PotGoTo(-10));
		
		shooter10.whenPressed(new PotGoTo(60));
		shooter11.whenPressed(new ShootBallCommand(0.40));
		shooter3.whileHeld(new WindowMotorControlScetchy());
		config1.whenPressed(new ConfigSave());
		config2.whenPressed(new ElevatorConfig());
	}

	/**
	 * used to get the leftJoysticks Y axis value
	 * 
	 * @return the position of the Y axis of the left {@link Joystick}. a value
	 *         between -1 and 1, negative being forward from the initial state
	 *         and positive values being behind the initial state.
	 */
	public double getLeftJoystickY() {
		return deadZone(leftJoystick.getY());
	}

	public double getShooterJoystickY() {
		return deadZone(shooterJoystick.getY());
	}

	/**
	 * used to get the rightJoysticks Y axis value
	 * 
	 * @return the position of the Y axis of the right {@link Joystick}. a value
	 *         between -1 and 1, negative being forward from the initial state
	 *         and positive values being behind the initial state.
	 */
	public double getRightJoystickY() {
		return deadZone(rightJoystick.getY());
	}

	public double getThrottle() {
		return deadZone(Math.abs((shooterJoystick.getRawAxis(2) - 1) / 2));
	}

	public double deadZone(double value) {
		if (value > Constants.DEAD_ZONE || value < -Constants.DEAD_ZONE) {
			return value;
		}
		return 0;
	}

	public void updateStatus() {
		SmartDashboard.putNumber("LeftJoystick", getLeftJoystickY());
		SmartDashboard.putNumber("RightJoystick", getRightJoystickY());
		SmartDashboard.putNumber("ShooterJoystick", getShooterJoystickY());
		SmartDashboard.putNumber("Throttle", getThrottle());
	}
}
