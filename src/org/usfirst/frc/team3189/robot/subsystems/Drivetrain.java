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

	private PingSonar frontSonar = new PingSonar(0);
	private PingSonar rightSonar = new PingSonar(1);
	private PingSonar backSonar = new PingSonar(2);
	private PingSonar leftSonar = new PingSonar(3);
	
	public static int min = 300;
	public static int max = 600;
	public double range = .05;
	
	public Drivetrain(){
		
		rightBackMotor.setInverted(true);
		rightFrontMotor.setInverted(true);
	}
	
	public void startSonars(){
		frontSonar.start();
		rightSonar.start();
		backSonar.start();
		leftSonar.start();
	}
	
	public void stopSonars(){
		frontSonar.stop();
		rightSonar.stop();
		backSonar.stop();
		leftSonar.stop();
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
		SmartDashboard.putNumber("left front Out V", leftFrontMotor.getOutputVoltage());
		SmartDashboard.putNumber("left front out C", leftFrontMotor.getOutputCurrent());
		SmartDashboard.putNumber("left front Bus V", leftFrontMotor.getBusVoltage());
		SmartDashboard.putNumber("right front Out V", rightFrontMotor.getOutputVoltage());
		SmartDashboard.putNumber("right front out C", rightFrontMotor.getOutputCurrent());
		SmartDashboard.putNumber("right front Bus V", rightFrontMotor.getBusVoltage());
		SmartDashboard.putNumber("left back Out V", leftBackMotor.getOutputVoltage());
		SmartDashboard.putNumber("left back out C", leftBackMotor.getOutputCurrent());
		SmartDashboard.putNumber("left back Bus V", leftBackMotor.getBusVoltage());
		SmartDashboard.putNumber("right back Out V", rightBackMotor.getOutputVoltage());
		SmartDashboard.putNumber("right back out C", rightBackMotor.getOutputCurrent());
		SmartDashboard.putNumber("right back Bus V", rightBackMotor.getBusVoltage());
		SmartDashboard.putNumber("Front Sonar", frontSonar.getInches());
		SmartDashboard.putNumber("Right Sonar", rightSonar.getInches());
		SmartDashboard.putNumber("Back Sonar", backSonar.getInches());
		SmartDashboard.putNumber("Left Sonar", leftSonar.getInches());
	}
	
}
