package org.usfirst.frc.team3189.robot.config_commands;

import org.usfirst.frc.team3189.robot.Constants;
import org.usfirst.frc.team3189.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ElevatorConfig extends Command {

    public ElevatorConfig() {
        requires(Robot.elevator);
    }

    protected void initialize() {
    	Robot.elevator.setZero();
    	Robot.elevator.setSpeed(Constants.ELEVATOR_LIFT_SPEED);
    }

    protected void execute() {
    	if(Robot.elevator.getHigherLimit() && Robot.elevator.getSpeed() > 0){
    		Constants.POT_MAX = Robot.elevator.getPot();
    		Robot.elevator.setSpeed(-Constants.ELEVATOR_LOWER_SPEED);
    	}
    }

    protected boolean isFinished() {
        return Robot.elevator.getLowerLimit();
    }

    protected void end() {
    	Constants.POT_MIN = Robot.elevator.getPot();
    	Constants.POINTS_PER_DEGREE = (Constants.POT_MAX - Constants.POT_VALUE_AT_ZERO) / Constants.ELEVATOR_HIGHEST_ANGLE;
    	Constants.saveConfig();
    }

    protected void interrupted() {
    }
}
