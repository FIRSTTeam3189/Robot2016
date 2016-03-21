package org.usfirst.frc.team3189.robot.config_commands;

import org.usfirst.frc.team3189.robot.Constants;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ConfigSave extends Command {

    public ConfigSave() {
    }

    protected void initialize() {
    	Constants.saveConfig();
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
