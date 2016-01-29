package org.usfirst.frc.team3189.robot.subsystems;

import org.usfirst.frc.team3189.robot.Constants;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.ColorMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.NIVision.MeasurementType;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CameraThread extends Thread {
	int session;
	Image frame;

	public static double lowestY;
	public static double highestY;
	public static double leftX;
	public static double rightX;
	public static double centerOfMassX;
	public static double centerOfMassY;

	@Override
	public void run() {
		NIVision.IMAQdxGrab(session, frame, 1);

		NIVision.imaqColorThreshold(frame, frame, 255, ColorMode.RGB, Constants.getRedRange(),
				Constants.getGreenRange(), Constants.getBlueRange());
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

		CameraServer.getInstance().setImage(frame);
	}

	@Override
	public synchronized void start() {
		super.start();
		session = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
		frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
		NIVision.IMAQdxConfigureGrab(session);
		NIVision.IMAQdxStartAcquisition(session);
	}

}
