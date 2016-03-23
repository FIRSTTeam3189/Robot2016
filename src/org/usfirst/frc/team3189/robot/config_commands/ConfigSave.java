package org.usfirst.frc.team3189.robot.config_commands;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ConfigSave extends Command {

    public ConfigSave() {
    	requires(Robot.drivetrain);
    }

    protected void initialize() {
    	Constants.saveConfig();
    	Constants.calcPotInfo();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return false;
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}
