package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.Range;

public class TestRecordOpMode extends TestBotHardware{
    
    public void loop(){
	int command;
	double deadzone = 0.01;
	
	float l_value = gamepad1.left_stick_y;
	float r_value = gamepad1.right_stick_y;

	l_value = Range.clip(l_value, -1, 1);
	r_value = Range.clip(r_value, -1, 1);

	/*
	  0 - stop
	  1 - forward
	  2 - turn right
	  3 - turn left
	  4 - backwards
	 */
	if(Math.abs(l_value) > deadzone && Math.abs(r_value) > deadzone){
	    if(l_value > 0 && r_value > 0){
		command = 1;
	    } else if( l_value > 0 && r_value < 0){
		command = 2;
	    } else if( r_value > 0 && l_value < 0){
		command = 3;
	    } else {
		command = 4;
	    }
	    left_motor.setPower(l_value);
	    right_motor.setPower(r_value);
	} else {
	    command = 0;
 	    left_motor.setPower(0.0);
	    right_motor.setPower(0.0);
	}

	record(command, l_value, r_value);

	telemetry.addData("lp", "Left Power" + l_value);
	telemetry.addData("rp", "Right Power" + r_value);
    }
}

