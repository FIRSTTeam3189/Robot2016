package org.usfirst.frc.team3189.robot.subsystems;

import java.awt.Point;
import java.io.DataInputStream;
import java.lang.instrument.Instrumentation;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

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
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.vision.USBCamera;

/**
 *
 */
public class VisionDisplay extends Subsystem {

	private static String KINECT_STREAMING = "online";
	private static String KINECT_SHUTDOWN = "shutdown";
	private static String KINECT_EXCEPTION = "exception";
	private static String KINECT_TABLE = "Vision";
	private static String PI_SERVER_NAME = "rasberrypi.local";
	private static int PI_SERVER_PORT = 4269;

	private USBCamera mycam;
	private CameraServer cam;
	private Image img;
	private int exposure;
	private long lastSent;
	private int camMode = 1;
	private Socket socket;

	private NetworkTable table;

	private double LeftTopX = -1;
	private double RightTopX = -1;
	private double RightBottomX = -1;
	private double LeftBottomX = -1;
	private double LeftTopY = -1;
	private double RightTopY = -1;
	private double RightBottomY = -1;
	private double LeftBottomY = -1;

	public void init() throws Exception {
		try {
			mycam = new USBCamera("cam0");
			mycam.openCamera();
			cam = CameraServer.getInstance();
			img = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);

			mycam.setBrightness(0);
			mycam.setExposureManual(3);

			table = NetworkTable.getTable(KINECT_TABLE);

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

	public void useLifeCam() {
		camMode = 1;
	}

	public boolean usingLifeCam() {
		return camMode == 1;
	}

	public void useKinect() {
		camMode = 2;
	}

	public boolean usingKinect() {
		return camMode == 2;
	}

	public void release() {
		camMode = 0;
	}

	public boolean unused() {
		return camMode == 0;
	}

	public Point getPoint() {
		return new Point((int) (LeftBottomX + RightTopX) / 2, (int) (LeftBottomY + RightTopY) / 2);
	}

	public void updateCameraFeed() throws Exception {
		if (cam != null && mycam != null) {
			if (Constants.CAM_BRIGHTNESS != mycam.getBrightness() || Constants.CAM_EXPOSURE != exposure) {
				change(Constants.CAM_EXPOSURE, Constants.CAM_BRIGHTNESS);
			}
			if (System.currentTimeMillis() - lastSent > 1000 / Constants.CAM_FRAMES_PER_SECOND) {
				if (usingKinect() && kinectStreaming()) {
					if (socket == null) {
						socket = new Socket(PI_SERVER_NAME, PI_SERVER_PORT);
					}
					DataInputStream dis = new DataInputStream(socket.getInputStream());
					byte[] bsize = new byte[4];
					dis.read(bsize);
					ByteBuffer bbsize = ByteBuffer.wrap(bsize).order(ByteOrder.LITTLE_ENDIAN);
					int size = bbsize.getInt();
					bsize = new byte[size];
					dis.read(bsize);
					// TODO put bytes in image.
				} else if (usingLifeCam()) {
					mycam.getImage(img);

					SmartDashboard.putString("point1", RightTopX + ":" + RightTopY);
					SmartDashboard.putString("point2", RightBottomX + ":" + RightBottomY);
					SmartDashboard.putString("point3", LeftBottomX + ":" + LeftBottomY);
					SmartDashboard.putString("point4", LeftTopX + ":" + LeftTopY);
					
					double left = Math.sqrt(Math.pow(LeftBottomX - LeftTopX, 2) + Math.pow(LeftBottomY - LeftTopY, 2));
					double right = Math.sqrt(Math.pow(RightBottomX - RightTopX, 2) + Math.pow(RightBottomY - RightTopY, 2));
					double top = Math.sqrt(Math.pow(RightTopX - LeftTopX, 2) + Math.pow(RightTopY - LeftTopY, 2));
					double bottom = Math.sqrt(Math.pow(LeftBottomX - RightBottomX, 2) + Math.pow(LeftBottomY - RightBottomY, 2));
					
					SmartDashboard.putNumber("perimeter", right + left + top + bottom);
					SmartDashboard.putNumber("centerx", (((RightTopX+LeftTopX)/2)+((RightBottomX+LeftBottomX)/2))/2);
					SmartDashboard.putNumber("centery", (((RightTopY+LeftTopY)/2)+((RightBottomY+LeftBottomY)/2))/2);
					SmartDashboard.putNumber("centerHeight", (left + right) / 2);
					SmartDashboard.putNumber("centerHeight", (bottom + top) / 2);
					
					NIVision.imaqFlip(img, img, FlipAxis.HORIZONTAL_AXIS);
					NIVision.imaqFlip(img, img, FlipAxis.VERTICAL_AXIS);

					CameraServer.getInstance().setImage(img);
				}
				lastSent = System.currentTimeMillis();
			}
		} else {
			throw new Exception("Camera is not Initialized");
		}
	}

	public void drawVision() {
		NIVision.imaqDrawLineOnImage(img, img, DrawMode.DRAW_VALUE,
				new NIVision.Point((int) (LeftTopX * Constants.CAM_WIDTH), (int) (LeftTopY * Constants.CAM_HEIGHT)),
				new NIVision.Point((int) (RightTopX * Constants.CAM_WIDTH), (int) (RightTopY * Constants.CAM_HEIGHT)),
				1.0f);
		NIVision.imaqDrawLineOnImage(img, img, DrawMode.DRAW_VALUE,
				new NIVision.Point((int) (RightTopX * Constants.CAM_WIDTH), (int) (RightTopY * Constants.CAM_HEIGHT)),
				new NIVision.Point((int) (RightBottomX * Constants.CAM_WIDTH),
						(int) (RightBottomY * Constants.CAM_HEIGHT)),
				1.0f);
		NIVision.imaqDrawLineOnImage(img, img, DrawMode.DRAW_VALUE,
				new NIVision.Point((int) (RightBottomX * Constants.CAM_WIDTH),
						(int) (RightBottomY * Constants.CAM_HEIGHT)),
				new NIVision.Point((int) (LeftBottomX * Constants.CAM_WIDTH),
						(int) (LeftBottomY * Constants.CAM_HEIGHT)),
				1.0f);
		NIVision.imaqDrawLineOnImage(img, img, DrawMode.DRAW_VALUE,
				new NIVision.Point((int) (LeftTopX * Constants.CAM_WIDTH), (int) (LeftTopY * Constants.CAM_HEIGHT)),
				new NIVision.Point((int) (LeftBottomX * Constants.CAM_WIDTH),
						(int) (LeftBottomY * Constants.CAM_HEIGHT)),
				1.0f);
		NIVision.imaqDrawLineOnImage(img, img, DrawMode.DRAW_VALUE,
				new NIVision.Point((int) (RightBottomX * Constants.CAM_WIDTH),
						(int) (RightBottomY * Constants.CAM_HEIGHT)),
				new NIVision.Point((int) (LeftTopX * Constants.CAM_WIDTH), (int) (LeftTopY * Constants.CAM_HEIGHT)),
				1.0f);
		NIVision.imaqDrawLineOnImage(img, img, DrawMode.DRAW_VALUE,
				new NIVision.Point((int) (RightTopX * Constants.CAM_WIDTH), (int) (RightTopY * Constants.CAM_HEIGHT)),
				new NIVision.Point((int) (LeftBottomX * Constants.CAM_WIDTH),
						(int) (LeftBottomY * Constants.CAM_HEIGHT)),
				1.0f);

		double angle = Robot.elevator.getAngle();
		int crossh = (int) (Constants.getPredictedCenterWidth(angle) * Constants.CAM_WIDTH) / 2;
		int crossv = (int) (Constants.getPredictedCenterHeight(angle) * Constants.CAM_HEIGHT) / 2;
		int centerx = (int) (Constants.getPredictedCenterX(angle) * Constants.CAM_WIDTH);
		int centery = (int) (Constants.getPredictedCenterY(angle) * Constants.CAM_HEIGHT);

		NIVision.imaqDrawLineOnImage(img, img, DrawMode.DRAW_VALUE, 
				new NIVision.Point(centerx - crossh, centery),
				new NIVision.Point(centerx + crossh, centery), 1.0f);
		NIVision.imaqDrawLineOnImage(img, img, DrawMode.DRAW_VALUE, 
				new NIVision.Point(centerx, centery - crossv),
				new NIVision.Point(centerx, centery + crossv), 1.0f);
	}

	public boolean kinectException() {
		return table.getBoolean(KINECT_EXCEPTION, false);
	}

	public void kinectShutdown(boolean shutdown) {
		table.putBoolean(KINECT_SHUTDOWN, shutdown);
	}

	public boolean kinectStreaming() {
		return table.getBoolean(KINECT_STREAMING, false);
	}

	public void updateTracking() {
		if (table != null) {
			double[][] points = { { table.getNumber("onex", RightTopX), table.getNumber("oney", RightTopY) },
					{ table.getNumber("twox", RightBottomX), table.getNumber("twoy", RightBottomY) },
					{ table.getNumber("threex", LeftBottomX), table.getNumber("threey", LeftBottomY) },
					{ table.getNumber("fourx", LeftTopX), table.getNumber("foury", LeftBottomY) } };
			LeftTopX = points[3][0];
			LeftTopY = points[3][1];
			RightTopX = points[0][0];
			RightTopY = points[0][1];
			LeftBottomX = points[2][0];
			LeftBottomY = points[2][1];
			RightBottomX = points[1][0];
			RightBottomY = points[1][1];
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
