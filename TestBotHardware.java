package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
//import com.qualcomm.ftcrobotccontroller.opmodes.recrun.*;

public class TestBotHardware //extends RecRunOpMode
{
    DcMotorController motor_controller;
    DcMotor left_motor;
    DcMotor right_motor;

    public TestBotHardware(){

    }
    public void init(){
	motor_controller = hardwareMap.dcMotorController.get("driveController");
	left_motor = hardwareMap.dcMotor.get("leftMotor");
	right_motor = hardwareMap.dcMotor.get("rightMotor");
	right_motor.setDirection(DcMotor.Direction.REVERSE);
    }
    public void start(){

    }
    public void loop(){

    }
    public void stop(){

    }

}
