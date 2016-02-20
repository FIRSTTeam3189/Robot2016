  package org.usfirst.frc.team3189.robot;

import org.usfirst.frc.team3189.robot.autonomous.AutonomousControl;
import org.usfirst.frc.team3189.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3189.robot.subsystems.Elevator;
import org.usfirst.frc.team3189.robot.subsystems.Gearbox;
import org.usfirst.frc.team3189.robot.subsystems.Kicker;
import org.usfirst.frc.team3189.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
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
	
	public static OI oi;
	
    Command autonomousCommand;
    SendableChooser chooser;

    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new AutonomousControl());
        SmartDashboard.putData("Auto mode", chooser);
        //drivetrain.startSonar();
        initStatus();
    }
	
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateStatus();
	}
	
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        
        updateStatus();
        
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        updateStatus();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
        updateStatus();
    }
    
    public void updateStatus(){
    	//visionThread.updateStatus();
    	drivetrain.updateStatus();
    }
    
    public void initStatus(){
    	//visionThread.initStatus();
    }
}
