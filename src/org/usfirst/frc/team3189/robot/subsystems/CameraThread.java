package org.usfirst.frc.team3189.robot.subsystems;

import java.util.function.Consumer;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.commands.DrivetrainControl;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ColorMode;
import com.ni.vision.NIVision.ContourFitPolynomialReport;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.ImageType;
import com.ni.vision.NIVision.MeasurementType;
import com.ni.vision.NIVision.Range;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CameraThread extends Thread {
	int session;
	Image frame;
	SendableChooser chooser;
	private Image processedFrame;
	private Runnable sendFunc;
	
	public static double lowestY;
	public static double highestY;
	public static double leftX;
	public static double rightX;
	public static double centerOfMassX;
	public static double centerOfMassY;
	
	public int redLow = 75;
	public int redHigh = 150;
	public int greenLow = 200;
	public int greenHigh = 255;
	public int blueLow = 200;
	public int blueHigh = 255;

	public Range getRedRange() {
		return new Range(redLow, redHigh);
	}

	public Range getGreenRange() {
		return new Range(greenLow, greenHigh);
	}

	public Range getBlueRange() {
		return new Range(blueLow, blueHigh);
	}
	
	public void initStatus(){
		SmartDashboard.putNumber("RedLow", redLow);
		SmartDashboard.putNumber("RedHigh", redHigh);
		SmartDashboard.putNumber("GreenHigh", greenHigh);
		SmartDashboard.putNumber("GreenLow", greenLow);
		SmartDashboard.putNumber("BlueHigh", blueHigh);
		SmartDashboard.putNumber("BlueLow", blueLow);
	}
	
	public void updateStatus(){
		redHigh = (int) SmartDashboard.getNumber("RedHigh", redHigh);
		redLow = (int) SmartDashboard.getNumber("RedLow", redLow);
		greenHigh = (int) SmartDashboard.getNumber("GreenHigh", greenHigh);
		greenLow = (int) SmartDashboard.getNumber("GreenLow", greenLow);
		blueHigh = (int) SmartDashboard.getNumber("BlueHigh", blueHigh);
		blueLow = (int) SmartDashboard.getNumber("BlueLow", blueLow);
	}

	@Override
	public void run() {
		initStatus();
		while (true) {
			updateStatus();
			NIVision.IMAQdxGrab(session, frame, 1);

			NIVision.imaqColorThreshold(frame, frame, 255, ColorMode.RGB, getRedRange(),
					getGreenRange(), getBlueRange());
			
			//ContourFitPolynomialReport report = NIVision.imaqContourFitPolynomial(frame, 8);
//			NIVision.imaqConvexHull(frame, frame, 4);
			int blobs = NIVision.imaqCountParticles(frame, 1);
			
			double biggest = 0;
			int index = 0;

			for (int i = 0; i < blobs; ++i) {
				double area = NIVision.imaqMeasureParticle(frame, i, 0, MeasurementType.MT_AREA);
				if (NIVision.imaqMeasureParticle(frame, i, 0, MeasurementType.MT_AREA) > biggest) {
					biggest = area;
					index = i;
				}
			}
			SmartDashboard.putNumber("Blobs", blobs);

			if (blobs > 0) {

				lowestY = NIVision.imaqMeasureParticle(frame, index, 0, MeasurementType.MT_BOUNDING_RECT_BOTTOM);
				highestY = NIVision.imaqMeasureParticle(frame, index, 0, MeasurementType.MT_BOUNDING_RECT_TOP);

				leftX = NIVision.imaqMeasureParticle(frame, index, 0, MeasurementType.MT_BOUNDING_RECT_LEFT);
				rightX = NIVision.imaqMeasureParticle(frame, index, 0, MeasurementType.MT_BOUNDING_RECT_RIGHT);

				centerOfMassX = NIVision.imaqMeasureParticle(frame, index, 0, MeasurementType.MT_CENTER_OF_MASS_X);
				centerOfMassY = NIVision.imaqMeasureParticle(frame, index, 0, MeasurementType.MT_CENTER_OF_MASS_Y);

				SmartDashboard.putNumber("centerOfMassX", centerOfMassX);
				SmartDashboard.putNumber("centerOfMassY", centerOfMassY);
			}
			((Action)chooser.getSelected()).run();
		}
	}

	@Override
	public synchronized void start() {
		chooser = new SendableChooser();
		
        chooser.addDefault("#NoFilter", (Action)(() -> {
        	CameraServer.getInstance().setImage(frame);
        }));
        chooser.addObject("#Threashhold", (Action) (() -> {
        	CameraServer.getInstance().setImage(frame);
        }));
        SmartDashboard.putData("Camera", chooser);
		session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		processedFrame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		NIVision.IMAQdxConfigureGrab(session);
		NIVision.IMAQdxStartAcquisition(session);
		super.start();
	}
	
	@FunctionalInterface
	public static interface Action {
		public abstract void run();
	}
	
}

