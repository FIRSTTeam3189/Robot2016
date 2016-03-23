package org.usfirst.frc.team3189.robot.subsystems;

import java.awt.Point;
import java.lang.instrument.Instrumentation;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.Robot;
import org.usfirst.frc.team3189.robot.RobotMap;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.FlipAxis;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.Rect;
import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 *
 */
public class VisionDisplay extends Subsystem {

	USBCamera mycam;
	CameraServer cam;
	Image img;
	int session;
	int exposure;

	public void init() throws Exception {
		try {
			mycam = new USBCamera("cam0");
			mycam.openCamera();
			cam = CameraServer.getInstance();
			img = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
			
			mycam.setBrightness(0);
			mycam.setExposureManual(3);
			
			//session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			//NIVision.IMAQdxConfigureGrab(session);
		} catch (Exception e) {
			mycam = null;
			cam = null;
			throw new Exception("Camera not found");
		}
	}

	public void start() throws Exception {
		if (cam != null && mycam != null) {
			mycam.startCapture();
		} else {
			throw new Exception("Camera is not Initialized");
		}
	}
	
	public void change(int e, int b){
		if(mycam != null){
			mycam.setBrightness(b);
			exposure = e;
			if(e >= 0){
				mycam.setExposureManual(e);
			}else{
				mycam.setExposureAuto();
			}
		}
	}

	public void end() throws Exception {
		if (cam != null && mycam != null) {
			mycam.stopCapture();
		} else {
			throw new Exception("Camera is not Initialized");
		}

	}

	public void update() throws Exception {
		if (cam != null && mycam != null) {
			if(Constants.CAM_BRIGHTNESS != mycam.getBrightness() || 
					Constants.CAM_EXPOSURE != exposure){
				change(Constants.CAM_EXPOSURE, Constants.CAM_BRIGHTNESS);
			}
			mycam.getImage(img);
			
			NIVision.imaqFlip(img, img, FlipAxis.HORIZONTAL_AXIS);
			NIVision.imaqFlip(img, img, FlipAxis.VERTICAL_AXIS);
			NIVision.imaqDrawLineOnImage(img, img, DrawMode.DRAW_VALUE, new NIVision.Point(10, 10), 
					new NIVision.Point(100, 100),  1.0f);
			CameraServer.getInstance().setImage(img);
		} else {
			throw new Exception("Camera is not Initialized");
		}

	}

	public Rect getRect() {
		double dist = Constants.getDistanceFromAngle(Robot.elevator.getAngle(), Constants.MAX_SPEED);
		return new Rect(100, 100, 400, 400);
	}

	public void initDefaultCommand() {
	}
}
