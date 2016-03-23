package org.usfirst.frc.team3189.robot.subsystems;

import java.awt.Point;
import java.lang.instrument.Instrumentation;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.Robot;
import org.usfirst.frc.team3189.robot.RobotMap;
import org.usfirst.frc.team3189.robot.commands.LifeCamVision;

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

	private USBCamera mycam;
	private CameraServer cam;
	private Image img;
	private int exposure;
	private long lastSent;
	private int camMode = 1;

	public void init() throws Exception {
		try {
			mycam = new USBCamera("cam0");
			mycam.openCamera();
			cam = CameraServer.getInstance();
			img = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

			mycam.setBrightness(0);
			mycam.setExposureManual(3);

			// session = NIVision.IMAQdxOpenCamera("cam0",
			// NIVision.IMAQdxCameraControlMode.CameraControlModeController);
			// NIVision.IMAQdxConfigureGrab(session);
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

	public void change(int e, int b) {
		if (mycam != null) {
			mycam.setBrightness(b);
			exposure = e;
			if (e >= 0) {
				mycam.setExposureManual(e);
			} else {
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
	
	public void useLifeCam(){
		camMode = 1;
	}
	
	public boolean usingLifeCam(){
		return camMode == 1;
	}
	
	public void useKinect(){
		camMode = 2;
	}
	
	public boolean usingKinect(){
		return camMode == 2;
	}
	
	public void release(){
		camMode = 0;
	}
	
	public boolean unused(){
		return camMode == 0;
	}

	public void update() throws Exception {
		if (cam != null && mycam != null) {
			if (Constants.CAM_BRIGHTNESS != mycam.getBrightness() || Constants.CAM_EXPOSURE != exposure) {
				change(Constants.CAM_EXPOSURE, Constants.CAM_BRIGHTNESS);
			}
			if (System.currentTimeMillis() - lastSent > 1000 / Constants.CAM_FRAMES_PER_SECOND) {
				if (usingKinect()) {
					
				} else if(usingLifeCam()) {
					mycam.getImage(img);

					NIVision.imaqFlip(img, img, FlipAxis.HORIZONTAL_AXIS);
					NIVision.imaqFlip(img, img, FlipAxis.VERTICAL_AXIS);
					NIVision.imaqDrawLineOnImage(img, img, DrawMode.DRAW_VALUE, new NIVision.Point(10, 10),
							new NIVision.Point(100, 100), 1.0f);

					CameraServer.getInstance().setImage(img);
				}
				lastSent = System.currentTimeMillis();
			}
		} else {
			throw new Exception("Camera is not Initialized");
		}

	}

	public Rect getRect() {
		double dist = Constants.getDistanceFromAngle(Robot.elevator.getAngle(), Constants.MAX_SPEED);
		return new Rect(100, 100, 400, 400);
	}

	public void initDefaultCommand() {
		setDefaultCommand(new LifeCamVision());
	}
}
