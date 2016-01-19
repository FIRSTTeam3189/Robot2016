package org.usfirst.frc.team3189.robot.utils;

import edu.wpi.first.wpilibj.Solenoid;

/**
 * A class to abstract the use of pistons containing two {@link Solenoid}
 * containing interfaces to Extend or Retract the {@link Piston}.
 * 
 * @author Nate, Improved by Dev
 */
public class Piston {
	/** the retracting {@link Solenoid} for the {@link Piston}. */
	private Solenoid retract;
	/** the extending {@link Solenoid} for the {@link Piston}. */
	private Solenoid extend;

	/**
	 * creates a new {@link Piston}
	 * 
	 * @param retractID
	 *            channel ID for the retracting {@link Solenoid} of the
	 *            {@link Piston}.
	 * @param extendID
	 *            channel ID for the extending {@link Solenoid} of the
	 *            {@link Piston}.
	 * @param retractedByDefault
	 *            if true sets the default state of the {@link Piston} to
	 *            having the {@link Piston} retracted or extended if false.
	 */
	public Piston(int retractID, int extendID, boolean retractedByDefault) {
		retract = new Solenoid(retractID);
		extend = new Solenoid(extendID);
		retractPiston(retractedByDefault);
	}

	/**
	 * creates a new piston.
	 * 
	 * @param retractID
	 *            channel ID for the retracting {@link Solenoid}.
	 * @param extendID
	 *            channel ID for the extending {@link Solenoid}.
	 */
	public Piston(int retractedID, int extendID) {
		this(retractedID, extendID, true);
	}

	/**
	 * sets the {@link Piston} to an extended state if passed true.
	 * 
	 * @param extend
	 *            sets the state of the {@link Piston} to extended if true or
	 *            retracted if false.
	 */
	public void extendPiston(boolean extend) {
		retract.set(!extend);
		this.extend.set(extend);
	}

	/**
	 * sets the {@link Piston} to an extended state.
	 */
	public void extendPiston() {
		extendPiston(true);
	}

	/**
	 * sets the {@link Piston} to a retracted state if passed true.
	 * 
	 * @param retract
	 *            sets the state of the {@link Piston} to retracted if true or
	 *            extended if false.
	 */
	public void retractPiston(boolean retract) {
		this.retract.set(retract);
		extend.set(!retract);
	}

	/**
	 * sets the {@link Piston} to a retracted state.
	 */
	public void retractPiston() {
		retractPiston(true);
	}

	/**
	 * used to check if the {@link Piston} is in the extended state.
	 * 
	 * @return true if {@link Piston} is in the extended state.
	 */
	public boolean isExtended() {
		return extend.get();
	}

	/**
	 * used to check if the {@link Piston} is in the retracted state.
	 * 
	 * @return true if {@link Piston} is in the retracted state.
	 */
	public boolean isRetracted() {
		return retract.get();
	}

	/**
	 * toggles the state of the piston from extended to retracted or retracted
	 * to extended depending on the state of the {@link Piston} at the time of
	 * being called.
	 */
	public void toggle() {
		retract.set(!retract.get());
		extend.set(!extend.get());
	}
}