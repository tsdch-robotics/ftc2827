package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.ftcrobotcontroller.opmodes.recrun.*;

public class Hardware extends RecRunOpMode
{
    DcMotorController drive_controller;
    DcMotorController pullup_controller;

    DcMotor left_drive;
    DcMotor right_drive;

    DcMotor left_pullup;
    DcMotor right_pullup;

    DcMotor right_arm;
    DcMotor left_arm;

    Servo left_hook;
    Servo right_hook;

    Servo left_plow;
    Servo right_plow;

    Servo left_climber;
    Servo right_climber;
    boolean left_climb_out = false;
    boolean right_climb_out = false;
    
    double upPower = 1.0;
    double downPower = -1.0;

    double left_arm_pos = 0.0;
    double right_arm_pos = 1.0;

    public Hardware() {
	super();
    }

    public void toggleLeftClimber() {
	left_climber.setPosition(left_climb_out ? 1.0 : 0.5);
	left_climb_out = !left_climb_out;
    }

    public void toggleRightClimber() {
	right_climber.setPosition(right_climb_out ? 0.0 : 0.5);
	right_climb_out = !right_climb_out;
    }

    public void init() {
	drive_controller = hardwareMap.dcMotorController.get("driveController");
	pullup_controller = hardwareMap.dcMotorController.get("pullupController");

	left_drive = hardwareMap.dcMotor.get("leftDrive");
	right_drive = hardwareMap.dcMotor.get("rightDrive");
	left_drive.setDirection(DcMotor.Direction.REVERSE);

	left_pullup = hardwareMap.dcMotor.get("leftPullup");
	right_pullup = hardwareMap.dcMotor.get("rightPullup");
	right_pullup.setDirection(DcMotor.Direction.REVERSE);

	left_hook = hardwareMap.servo.get("leftHook");
	right_hook = hardwareMap.servo.get("rightHook");

	left_plow = hardwareMap.servo.get("leftPlow");
	right_plow = hardwareMap.servo.get("rightPlow");
	left_plow.scaleRange(0.45, 1.0);
	right_plow.scaleRange(0.0, 0.75);
        right_plow.setPosition(0.0);
	left_plow.setPosition(1.0);

	left_climber = hardwareMap.servo.get("leftClimber");
	right_climber = hardwareMap.servo.get("rightClimber");
	left_climber.scaleRange(0.0, 1.0);
	right_climber.scaleRange(0.0, 1.0);

	left_arm = hardwareMap.dcMotor.get("leftArm");
	right_arm = hardwareMap.dcMotor.get("rightArm");
	right_arm.setDirection(DcMotor.Direction.REVERSE);
	
	left_climber.setPosition(1.0);
	right_climber.setPosition(0.0);
	left_hook.setPosition(0.5);
	right_hook.setPosition(0.5);
    }

    public void start() {

    }

    public void loop() {

    }

    public void stop() {

    }

}
