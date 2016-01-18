package org.usfirst.frc.team3189.robot.utils;

import edu.wpi.first.wpilibj.Solenoid;

public class Piston {
	private Solenoid retract;
	private Solenoid extend;

	public Piston(int retractID, int extendID, boolean isRetracted) {
		retract = new Solenoid(retractID);
		extend = new Solenoid(extendID);
		retract.set(isRetracted);
		extend.set(!isRetracted);
	}

	public Piston(int retractedID, int extendID) {
		this(retractedID, extendID, true);
	}

	public void extendPiston() {
		retract.set(false);
		extend.set(true);
	}

	public void retractPiston() {
		retract.set(true);
		extend.set(false);
	}

	public boolean isExtended() {
		return extend.get();
	}

	public boolean isRetracted() {
		return retract.get();
	}

	public void toggle() {
		retract.set(!retract.get());
		extend.set(!extend.get());
	}
}