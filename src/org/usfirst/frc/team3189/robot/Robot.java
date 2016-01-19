
package org.usfirst.frc.team3189.robot;

import org.usfirst.frc.team3189.robot.commands.DrivetrainControl;
import org.usfirst.frc.team3189.robot.subsystems.Compressor;
import org.usfirst.frc.team3189.robot.subsystems.Drivetrain;
import org.usfirst.frc.team3189.robot.subsystems.Kicker;
import org.usfirst.frc.team3189.robot.subsystems.Gearbox;
import org.usfirst.frc.team3189.robot.subsystems.Shooter;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * the main robot code
 * @author Alex Nate Mitch
 *
 */


/*
 * integrates the drivetrain shooter compressor and gearbox into the robot
 */
public class Robot extends IterativeRobot {

	public static final Drivetrain drivetrain = new Drivetrain();
	public static final Shooter Shooter = new Shooter();
	public static final Compressor Compressor = new Compressor(); 
	public static final Gearbox gearbox = new Gearbox();
	public static OI oi;

	public static Kicker Kicker = new Kicker();
    Command autonomousCommand;
    SendableChooser chooser;

    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new DrivetrainControl());
        SmartDashboard.putData("Auto mode", chooser);
    }
	
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

/*
 * autonomous functions
 */
	
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
        
		
        if (autonomousCommand != null) autonomousCommand.start();
    }

    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    public void teleopPeriodic() {
        Scheduler.getInstance().run();
    }
    
    public void testPeriodic() {
        LiveWindow.run();
    }
}
