package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController;

public class ScrimmageHardware extends OpMode
{
    DcMotorController drive_controller;
    DcMotorController pullup_controller;

    DcMotor left_drive;
    DcMotor right_drive;

    DcMotor left_pullup;
    DcMotor right_pullup;

    Servo left_hook;
    Servo right_hook;

    public ScrimmageHardware() {
	
    }

    public void init() {
	drive_controller = hardwareMap.dcMotorController.get("driveController");
	pullup_controller = hardwareMap.dcMotorController.get("pullupController");

	left_drive = hardwareMap.dcMotor.get("leftDrive");
	right_drive = hardwareMap.dcMotor.get("rightDrive");
	right_drive.setDirection(DcMotor.Direction.REVERSE);

	left_pullup = hardwareMap.dcMotor.get("leftPullup");
	right_pullup = hardwareMap.dcMotor.get("rightPullup");
	right_pullup.setDirection(DcMotor.Direction.REVERSE);

	left_hook = hardwareMap.servo.get("leftHook");
	right_hook = hardwareMap.servo.get("rightHook");
    }

    public void start() {

    }

    public void loop() {

    }

    public void stop() {

    }

}
