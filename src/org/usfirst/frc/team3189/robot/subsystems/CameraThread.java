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
			double centerX = NIVision.imaqMeasureParticle(frame, index, 0, MeasurementType.MT_FIRST_PIXEL_X_);
			double centerY = NIVision.imaqMeasureParticle(frame, index, 0, MeasurementType.MT_FIRST_PIXEL_Y);
			double rightBottom = NIVision.imaqMeasureParticle(frame, index, 0, MeasurementType.MT_BOUNDING_RECT_BOTTOM);
			SmartDashboard.putNumber("centerX", centerX);
			SmartDashboard.putNumber("centerY", centerY);
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
