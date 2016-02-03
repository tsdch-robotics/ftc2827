package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.ftcrobotcontroller.opmodes.recrun.*;

public class TestHardware extends RecRunOpMode
{
    DcMotorController drive_controller;
    DcMotor left_drive;
    DcMotor right_drive;

    public TestHardware() {
	super();
    }

    public void init() {
	drive_controller = hardwareMap.dcMotorController.get("driveController");
	left_drive = hardwareMap.dcMotor.get("leftDrive");
	right_drive = hardwareMap.dcMotor.get("rightDrive");
	right_drive.setDirection(DcMotor.Direction.FORWARD);
	left_drive.setDirection(DcMotor.Direction.REVERSE);
    }

    public void setDriveMode(DcMotorController.RunMode mode) {
        if (left_drive.getChannelMode() != mode) {
            left_drive.setChannelMode(mode);
        }
        if (right_drive.getChannelMode() != mode) {
            right_drive.setChannelMode(mode);
        }
    }

    public void start() {

    }

    public void loop() {

    }

    public void stop() {

    }

}
