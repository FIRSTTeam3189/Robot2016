package org.usfirst.frc.team3189.robot;

import org.usfirst.frc.team3189.robot.autonomous.AutoForward;
import org.usfirst.frc.team3189.robot.autonomous.AutoForwardLowBar;
import org.usfirst.frc.team3189.robot.autonomous.AutonomousControl;
import org.usfirst.frc.team3189.robot.autonomous.DoNothing;
import org.usfirst.frc.team3189.robot.autonomous.TeachingAutoIgnore;
import org.usfirst.frc.team3189.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3189.robot.subsystems.Elevator;
import org.usfirst.frc.team3189.robot.subsystems.Gearbox;
import org.usfirst.frc.team3189.robot.subsystems.Kicker;
import org.usfirst.frc.team3189.robot.subsystems.RobotEncoder;
import org.usfirst.frc.team3189.robot.subsystems.Shooter;
import org.usfirst.frc.team3189.robot.subsystems.VisionDisplay;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Shooter shooter = new Shooter();
	public static final Gearbox gearbox = new Gearbox();
	public static final Kicker kicker = new Kicker();
	public static final Elevator elevator = new Elevator();
	public static final VisionDisplay cam = new VisionDisplay();
	public static final RobotEncoder encoder = new RobotEncoder();
	public static boolean isDisabled = true;

	public static OI oi;
	public static PowerDistributionPanel pdp;
	
	private short count = 0;

	Command autonomousCommand;
	SendableChooser chooser;

	public void robotInit() {
		oi = new OI();
		pdp = new PowerDistributionPanel();
		chooser = new SendableChooser();
		chooser.addDefault("Low Bar", new AutoForwardLowBar());
		chooser.addObject("Do Nothing", new DoNothing());
		chooser.addObject("Forward", new AutoForward());
		chooser.addDefault("Dameon's code", new TeachingAutoIgnore());
		
		SmartDashboard.putData("Auto mode", chooser);
		//TODO write auto code
		//Constants.loadConfig();
		initStatus();
		try {
			cam.init();
			cam.start();
		} catch (Exception e) {

		}
		drivetrain.startSonars();
	}

	public void disabledInit() {
		if(count < 1){
			cam.kinectShutdown(false);
		}
		count++;
		isDisabled = true;
	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateStatus();
	}

	public void autonomousInit() {
		autonomousCommand = (Command) chooser.getSelected();

		if (autonomousCommand != null)
			autonomousCommand.start();
		isDisabled = false;
	}

	public void autonomousPeriodic() {
		Scheduler.getInstance().run();

		updateStatus();

	}

	public void teleopInit() {
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		isDisabled = false;
	}

	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateStatus();
	}

	public void testPeriodic() {
		LiveWindow.run();
		updateStatus();
	}

	public void updateStatus() {
		Constants.updateStatus();
		drivetrain.updateStatus();
		shooter.updateStatus();
		elevator.updateStatus();
		oi.updateStatus();
		cam.updateTracking();
		
		try{
    		Robot.cam.updateCameraFeed();
    	}catch(Exception e){
    		
    	}

		for (int i = 0; i < 16; ++i) {
			SmartDashboard.putNumber("PDP channel " + i, pdp.getCurrent(i));
		}
	}

	public void initStatus() {
		Constants.initStatus();
	}
}
