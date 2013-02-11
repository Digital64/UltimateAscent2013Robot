// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.


package org.usfirst.frc2877.UltimateAscent2013Robot.subsystems;

import org.usfirst.frc2877.UltimateAscent2013Robot.RobotMap;
import org.usfirst.frc2877.UltimateAscent2013Robot.OI;
import org.usfirst.frc2877.UltimateAscent2013Robot.commands.*;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.can.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc2877.UltimateAscent2013Robot.Robot;


/**
 *
 */
public class DriveTrain extends Subsystem {
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    CANJaguar jaguarLeftFront = RobotMap.driveTrainJaguarLeftFront;
    CANJaguar jaguarRightFront = RobotMap.driveTrainJaguarRightFront;
    CANJaguar jaguarLeftBack = RobotMap.driveTrainJaguarLeftBack;
    CANJaguar jaguarRightBack = RobotMap.driveTrainJaguarRightBack;
    RobotDrive robotDrive41 = RobotMap.driveTrainRobotDrive41;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public DriveTrain() {
           System.out.println("driveTrain constructed.");
    }
    
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND
	
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
        System.out.println("driveTrain.initDefaultCommand called.");

  
         
    }
    public void runMotor1 ( double speed ) {
        System.out.println("driveTrain.runMotor1. About to call jaguarLeftFront.set()");
        System.out.print ("The value of speed is ");
        System.out.println(speed);
 
        try{
        jaguarLeftFront.setX(speed);
        } catch (CANTimeoutException ex){
            System.out.println("Timeout Exception on jaguarLeftFront.setX in runMotor1");
        }
    }
    public void runMotor2 ( double speed ) {
        System.out.println("drivetrain.runMotor2. About to call jaguarLeftBack.set()");
        System.out.print("The value of speed is ");
        System.out.println(speed); 
        
        try{
        jaguarLeftBack.setX(speed);
        } catch (CANTimeoutException ex){
            System.out.println("Timeout Exception on jaguarLeftBack.setX in runMotor2");
        }
    }
    public void runMotor3( double speed ) {
        System.out.println("driveTrain.runMotor3. About to call jaguarRightBack.set()");
        System.out.print ("The value of speed is ");
        System.out.println(speed);
 
        try{
        jaguarRightBack.setX(speed);
        } catch (CANTimeoutException ex){
            System.out.println("Timeout Exception on jaguarRightBack.setX in runMotor3");
        }
    }
    public void runMotor4 ( double speed ) {
        System.out.println("drivetrain.runMotor4. About to call jaguarRightFront.set()");
        System.out.print("The value of speed is ");
        System.out.println(speed); 
        
        try{
        jaguarRightFront.setX(speed);
        } catch (CANTimeoutException ex){
            System.out.println("Timeout Exception on jaguarRightFront.setX in runMotor4");
        }
    }
    public void drive ( double x, double y, boolean mecanumOn ) {

        if (mecanumOn == true) {
            robotDrive41.mecanumDrive_Cartesian(x, y, 0, 0);
            System.out.println("Mecanum is ON");
    } else {
            robotDrive41.arcadeDrive(x, y);
            System.out.println("Mecanum is OFF");
        }
    }
}

