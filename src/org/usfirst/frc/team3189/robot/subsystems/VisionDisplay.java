package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.Robot;
import org.usfirst.frc.team3189.robot.RobotMap;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Rect;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class VisionDisplay extends Subsystem {
    
	CameraServer cam;
	Image img;
	int session;
	
	public void init(){
		cam = CameraServer.getInstance();
		img = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);
	}
	
	public void start(){
		NIVision.IMAQdxStartAcquisition(session);
	}
	
	public void end(){
		NIVision.IMAQdxStopAcquisition(session);
	}
	
    public void update(){
    	Rect rect = getRect();
    	
    	NIVision.IMAQdxGrab(session, img, 1);
        NIVision.imaqDrawShapeOnImage(img, img, rect,
                DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 0.0f);
        
        CameraServer.getInstance().setImage(img);
    }
    
    public Rect getRect(){
    	double dist = Constants.getDistanceFromAngle(Robot.elevator.getAngle(),
    			Constants.MAX_SPEED);
    	return new Rect();
    }

    public void initDefaultCommand() {}
}

