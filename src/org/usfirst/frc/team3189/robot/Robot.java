  package org.usfirst.frc.team3189.robot;

import org.usfirst.frc.team3189.robot.commands.DrivetrainControl;
import org.usfirst.frc.team3189.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3189.robot.subsystems.Shooter;
import org.usfirst.frc.team3189.robot.subsystems.WindowMotors;
import org.usfirst.frc.team3189.robot.subsystems.CameraThread;
import org.usfirst.frc.team3189.robot.subsystems.CompressorSubsystem;
import org.usfirst.frc.team3189.robot.subsystems.Gearbox;
import org.usfirst.frc.team3189.robot.subsystems.Kicker;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends IterativeRobot {

	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Shooter Shooter = new Shooter();
	public static final CompressorSubsystem Compressor = new CompressorSubsystem(); 
	public static final Gearbox gearbox = new Gearbox();
	public static final Kicker Kicker = new Kicker();
	public static final WindowMotors windowMotors = new WindowMotors();
	
	public static OI oi;

	
    Command autonomousCommand;
    SendableChooser chooser;
    CameraThread visionThread;

    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new DrivetrainControl());
        SmartDashboard.putData("Auto mode", chooser);
        visionThread = new CameraThread();
        //visionThread.start();
        //drivetrain.startSonar();
        initStatus();
    }
	
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateStatus();
	}

/*
 * autonomous functions
 * James likes men
 * Dev likes that James likes men
 */
	
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
