package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class TestBotForward extends TestBotHardware
{
    public void loop(){
	right_motor.setPower(1.0);
	left_motor.setPower(1.0);
    }
}
