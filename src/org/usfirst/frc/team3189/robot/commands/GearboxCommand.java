package org.usfirst.frc.team3189.robot.commands;

import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * Changes the state of the piston 
 * @author Alex
 *
 */
public class GearboxCommand extends Command {
/**
 * Holds the interface for the gearbox
 */
    public GearboxCommand() {
    	requires(Robot.gearbox);
    }
/**
 * Toggles the piston state 
 */
    protected void initialize() {
    	Robot.gearbox.togglePistonState();
    }

    protected void execute() {
    }
/**
 * Returns a true value every time the piston is called 
 */
    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
