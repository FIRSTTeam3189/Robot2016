package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.commands.DrivetrainControl;
import org.usfirst.frc.team3189.robot.utils.PingSonar;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * provides an interface for the drivetrain of the 2016 team 3189 robot.
 * 
 * @author Nate, Alex, Mitch
 */
public class Drivetrain extends Subsystem {
	
	private boolean reverse = false;

	/**
	 * the {@link SpeedController} for the front left motor of the
	 * {@link Drivetrain}
	 */
	private CANTalon leftFrontMotor = new CANTalon(RobotMap.leftfrontMotor);
	/**
	 * the {@link SpeedController} for the front right motor of the
	 * {@link Drivetrain}
	 */
	private CANTalon rightFrontMotor = new CANTalon(RobotMap.rightfrontMotor);
	/**
	 * the {@link SpeedController} for the back left motor of the
	 * {@link Drivetrain}
	 */
	private CANTalon leftBackMotor = new CANTalon(RobotMap.leftbackMotor);
	/**
	 * the {@link SpeedController} for the back right motor of the
	 * {@link Drivetrain}
	 */
	private CANTalon rightBackMotor = new CANTalon(RobotMap.rightbackMotor);

	private PingSonar sonar = new PingSonar(0);
	public static int min = 300;
	public static int max = 600;
	public double range = .05;
	
	public Drivetrain(){
		
		rightBackMotor.setInverted(true);
		rightFrontMotor.setInverted(true);
	}
	
	public void startSonar(){
		sonar.start();
	}
	
	public void stopSonar(){
		sonar.stop();
	}
	
	
	/**
	 * sets the speed of the drive wheels of the {@link Drivetrain}.
	 * 
	 * @param left
	 *            the desired speed to set the left wheels of the
	 *            {@link Drivetrain} to between -1 and 1, negative being
	 *            reversed.
	 * @param right
	 *            the desired speed to set the right wheels of the
	 *            {@link Drivetrain} to between -1 and 1, negative being
	 *            reversed.
	 */
	public void setspeed(double left, double right) {
		if (!reverse) {
			leftFrontMotor.set(left);
			rightFrontMotor.set(right);
			leftBackMotor.set(left);
			rightBackMotor.set(right);
		} else {
			leftFrontMotor.set(right);
			rightFrontMotor.set(left);
			leftBackMotor.set(right);
			rightBackMotor.set(left);	
		}
		
		
	}
	
	public void invert() {
		leftFrontMotor.setInverted(!leftFrontMotor.getInverted());
		rightFrontMotor.setInverted(!rightFrontMotor.getInverted());
		leftBackMotor.setInverted(!leftBackMotor.getInverted());
		rightBackMotor.setInverted(!rightBackMotor.getInverted());
		reverse = !reverse;
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new DrivetrainControl());
	}
	
	public void updateStatus() {
		
		SmartDashboard.putNumber("Sonar", sonar.getInches());
	}
	
}
