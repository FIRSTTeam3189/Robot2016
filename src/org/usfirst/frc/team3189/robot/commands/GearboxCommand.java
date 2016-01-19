package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Switches the state of the gearbox piston
 * @author Alex
 */
public class GearboxCommand extends Command {
/**
 * Requires the gearbox subsystem
 */
    public GearboxCommand() {
    	requires(Robot.gearbox);
    }

    /**
     * Toggles the state of the piston as son as the command is initialized
     */
    protected void initialize() {
    	Robot.gearbox.togglePistonState();
    }

    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    /**
     * Returns true as soon as the intialize is over
     */
    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
