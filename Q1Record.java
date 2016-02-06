package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.recrun.*;
import com.qualcomm.robotcore.util.Range;

public class Q1Record extends Hardware {

    boolean gamepad1_x_pressed = false;
    boolean gamepad1_b_pressed = false;
    double deadzone = 0.05;

    public Q1Record(){
	super();
    }

    public void loop() {
	//driving
	float r_power = gamepad1.left_stick_y;
	float l_power = gamepad1.right_stick_y;
	l_power = Range.clip(l_power, -1, 1);
	r_power = Range.clip(r_power, -1, 1);

	if(Math.abs(l_power) > deadzone || Math.abs(r_power) > deadzone){
	    record(1, l_power, r_power);
	    left_drive.setPower(l_power);
	    right_drive.setPower(r_power);
	} else {
	    record(1, 0.0, 0.0);
	    left_drive.setPower(0.0);
	    right_drive.setPower(0.0);
	}

	//plow
	if (gamepad1.y) {
	    record(2, 1.0, 0.0);
	    right_plow.setPosition(0.0);
	    left_plow.setPosition(1.0);
	} else if (gamepad1.a) {
	    record(2, 0.0, 0.0);
	    right_plow.setPosition(1.0);
	    left_plow.setPosition(0.0);
	}
    }
    public void init_loop(){
	telemetry.addData("msg:", "Do you want to play a game? ... ");
    }
    public void init(){
	super.init();
    }

    public void stop(){
	try{
	    writeFile();
	} catch(Exception e){
	    telemetry.addData("03", e.toString());
	}
    }
}
