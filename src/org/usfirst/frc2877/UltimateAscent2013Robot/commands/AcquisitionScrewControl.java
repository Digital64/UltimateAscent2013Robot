/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.usfirst.frc2877.UltimateAscent2013Robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;

/**
 *
 * @author Administrator
 */
public class AcquisitionScrewControl extends Command {
    
    // save the requested number of turns
    private int m_requestedTurns = 0;
    // keep track of how many turns
    private int m_turns;
    // keep track of how many iterations we've done
    private int numCycles = 0;
    // This constant is used to define how many cycles we need to go to make
    // sure that the cam has cleared the limit switch
    private final int MIN_CYCLES_TO_CLEAR = 20;
    // In case the switch can be set for multiple iterations
    // It's initialized to false because we won't check it until after we have
    // run MIN_CYCLLES_TO_CLEAR iterations.
    private boolean m_switchContacted = false;
    // The default speed for the screws
    double run = Robot.acquisition.ACQUISITIONSPEED;
    boolean limitSwitchTriggered;
    public AcquisitionScrewControl(int turns) {
        setInterruptible(false);
        m_requestedTurns = turns;
        
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires (Robot.acquisition);
        System.out.println("AcquisitionOverride constructor called");
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        // if M-requested turns is less than zero, then we have to move the
        // screws down, so make the speed negative.
        if (m_requestedTurns < 0) {
            run = -1 * run;
        }
        System.out.println("Initialize AcquisitionOverride");
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        // We need to run a few cycles before we check for the switch or else
        // it migh stop immediately.
        // If the switch state has changed, then if it is now true that means
        // we just finished a rotation.
        if (numCycles > MIN_CYCLES_TO_CLEAR && 
                Robot.acquisition.rotaryLimitSwitch.get() != m_switchContacted &&
                m_switchContacted) {
                    m_turns++;
                    // if we have completed the requested number of turns,
                    // we can go straight to end.
                    if (m_turns == m_requestedTurns) {
                        end();
                    }
                } else {
                    // Since the switch is not contacted, we have not completed
                    // the current turn, so keep going.
                    Robot.acquisition.acquisitionTurnScrews(run);
                }
        // Save the current state of the switch to check against next iteration
        m_switchContacted = Robot.acquisition.rotaryLimitSwitch.get();
        // increment the number of cycles
        numCycles++;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        // It si finished it we have completed the right number of turns.
        // So the switch must be contacted and m_turns must be equal to the
        // requested number. I made it >= just in case.
       return (m_turns >= m_requestedTurns);
    }

    // Called once after isFinished returns true
    protected void end() {
        Robot.acquisition.acquisitionTurnScrews(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
