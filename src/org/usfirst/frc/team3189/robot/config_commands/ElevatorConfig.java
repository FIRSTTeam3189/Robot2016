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
    	Robot.elevator.setSpeedSafe(-Constants.ELEVATOR_SLOW_LIFT_SPEED);
    }

    protected void execute() {
    	if(Robot.elevator.getHigherLimit() && Robot.elevator.getSpeed() < 0){
    		Constants.POT_LOWER = Robot.elevator.getPot();
    		Robot.elevator.setSpeedSafe(Constants.ELEVATOR_SLOW_LOWER_SPEED);
    	}else if(Robot.elevator.getSpeed() < 0){
    		Robot.elevator.setSpeedSafe(-Constants.ELEVATOR_SLOW_LIFT_SPEED);
    	}else{
    		Robot.elevator.setSpeedSafe(Constants.ELEVATOR_SLOW_LOWER_SPEED);
    	}
    }

    protected boolean isFinished() {
        return Robot.elevator.getLowerLimit() || 
        		Robot.elevator.getPot() > 999 || 
        		Robot.elevator.getPot() < 25;
    }

    protected void end() {
    	Constants.POT_UPPER = Robot.elevator.getPot();
    	Constants.saveConfig();
    	Constants.calcPotInfo();
    }

    protected void interrupted() {
    }
}
