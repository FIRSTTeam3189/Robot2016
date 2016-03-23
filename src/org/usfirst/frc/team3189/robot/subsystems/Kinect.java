package org.usfirst.frc.team3189.robot.subsystems;

import java.awt.Point;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Kinect extends Subsystem {

	private NetworkTable table;

	private double LeftTopX = -1;
	private double RightTopX = -1;
	private double RightBottomX = -1;
	private double LeftBottomX = -1;
	private double LeftTopY = -1;
	private double RightTopY = -1;
	private double RightBottomY = -1;
	private double LeftBottomY = -1;

	public void update() {
		if (table == null) {
			table = NetworkTable.getTable("Vision");
		}
		if (table != null) {
			double[][] points = { { table.getNumber("onex", LeftTopX), table.getNumber("oney", LeftTopY) },
					{ table.getNumber("twox", RightTopX), table.getNumber("twoy", RightTopY) },
					{ table.getNumber("threex", RightBottomX), table.getNumber("threey", RightBottomY) },
					{ table.getNumber("fourx", LeftBottomX), table.getNumber("foury", LeftBottomY) } };
			SmartDashboard.putString("point1", points[0][0] + ":" + points[0][1]);
			SmartDashboard.putString("point2", points[1][0] + ":" + points[1][1]);
			SmartDashboard.putString("point3", points[2][0] + ":" + points[2][1]);
			SmartDashboard.putString("point4", points[3][0] + ":" + points[3][1]);
		}
	}

	public void initDefaultCommand() {
	}
}
