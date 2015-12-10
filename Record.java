package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.Range;
import com.qualcomm.ftcrobotcontroller.opmodes.recrun.*;

public class Record extends RecRunOpMode{
    
    public Record(){
	super();
	//telemetry.addData("02", "contructor");
    }
    
    public void loop(){
	int command;
	double deadzone = 0.01;
	
	float l_value = gamepad1.left_stick_y;
	float r_value = gamepad1.right_stick_y;

	l_value = Range.clip(l_value, -1, 1);
	r_value = Range.clip(r_value, -1, 1);

	//telemetry.addData("02", "Loop started");

	/*
	  0 - stop
	  1 - forward
	  2 - rot right
	  3 - rot left
	  4 - turn right
	  5 - turn left
	  6 - back
	 */
	if(Math.abs(l_value) > deadzone || Math.abs(r_value) > deadzone){
	    if(l_value > 0 && r_value > 0){
		command = 1;
	    } else if( l_value > 0 && r_value < 0){
		command = 2;
	    } else if( r_value > 0 && l_value < 0){
		command = 3;
	    } else if( l_value > 0){
		command = 4;
	    } else if( r_value > 0){
		command = 5;
	    } else {
		command = 6;
	    }
	} else {
	    command = 0;
	    l_value = 0.0f;
	    r_value = 0.0f;
	}

	//telemetry.addData("02", "cmd:\t" + command);
	telemetry.addData("01", "l_v:\t" + l_value + "\tr_v:\t" + r_value);
	//telemetry.addData("01", "command: " + command);
	record(command, l_value, r_value);


    }
    public void stop(){
	try{
	    writeFile();
	} catch(Exception e){
	    telemetry.addData("03", e.toString());
	}
    }
}


