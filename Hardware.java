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

    Servo left_hook;
    Servo right_hook;
    Servo right_arm;
    Servo left_arm;

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
	left_climber.setPosition(left_climb_out ? 0 : 1.0);
	left_climb_out = !left_climb_out;
    }

    public void toggleRightClimber() {
	right_climber.setPosition(right_climb_out ? 0 : 1.0);
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

	left_climber = hardwareMap.servo.get("leftClimber");
	right_climber = hardwareMap.servo.get("rightClimber");
	left_climber.scaleRange(0.0, 1.0);
	right_climber.scaleRange(0.0, 1.0);

	left_arm = hardwareMap.servo.get("leftArm");
	right_arm = hardwareMap.servo.get("rightArm");
	left_arm.scaleRange(0.0, 0.16);
	right_arm.scaleRange(0.86, 1.0);

	left_climber.setPosition(0.0);
	right_climber.setPosition(1.0);
	left_arm.setPosition(0.0);
	right_arm.setPosition(1.0);
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
