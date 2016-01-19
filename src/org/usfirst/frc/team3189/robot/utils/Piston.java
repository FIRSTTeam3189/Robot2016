package org.usfirst.frc.team3189.robot.utils;

import edu.wpi.first.wpilibj.Solenoid;

/**
 * A helper class to control pistons
 * 
 * @author Nate
 */
public class Piston {
	private Solenoid retract;
	private Solenoid extend;

	/**
	 * creates a new piston
	 * 
	 * @param retractID
	 *            channel ID for the retract solenoid
	 * @param extendID
	 *            channel ID for the extending solenoid
	 * @param isRetracted
	 *            boolean value for whether the piston is extended or retracted
	 */
	public Piston(int retractID, int extendID, boolean isRetracted) {
		retract = new Solenoid(retractID);
		extend = new Solenoid(extendID);
		retract.set(isRetracted);
		extend.set(!isRetracted);
	}

	/**
	 * sets defult values
	 * 
	 * @param retractedID
	 *            channel ID for the retract solenoid
	 * @param extendID
	 *            channel ID for the extending soleniod
	 */
	public Piston(int retractedID, int extendID) {
		this(retractedID, extendID, true);
	}

	/**
	 * sets values to extend piston
	 */
	public void extendPiston() {
		retract.set(false);
		extend.set(true);
	}

	/**
	 * sets values to retract piston
	 */
	public void retractPiston() {
		retract.set(true);
		extend.set(false);
	}

	/**
	 * 
	 * checks whether the piston is extended
	 */
	public boolean isExtended() {
		return extend.get();
	}

	/**
	 * 
	 * checks whether the piston is retracted
	 */
	public boolean isRetracted() {
		return retract.get();
	}

	/**
	 * toggles whether the piston is retracted or extended
	 */
	public void toggle() {
		retract.set(!retract.get());
		extend.set(!extend.get());
	}
}