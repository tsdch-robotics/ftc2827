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
	boolean recorded = false;
	//driving
	float r_power = gamepad1.left_stick_y;
	float l_power = gamepad1.right_stick_y;
	l_power = Range.clip(l_power, -1, 1);
	r_power = Range.clip(r_power, -1, 1);
	if(Math.abs(l_power) > deadzone || Math.abs(r_power) > deadzone){
	    record(1, l_power, r_power);
	    recorded = true;
	    left_drive.setPower(l_power);
	    right_drive.setPower(r_power);
	}

	//climbers
	if (!gamepad1_x_pressed && gamepad1.x) {
	    record(2, 1.0, 0.0);
	    recorded = true;
	    toggleLeftClimber();
	}
	if (!gamepad1_b_pressed && gamepad1.b) {
	    record(2, 0.0, 1.0);
	    recorded = true;
	    toggleRightClimber();
	}
	
	//arms
	if(gamepad2.left_bumper){
	    record(3, 1.0, 0.0);
	    recorded = true;
	    left_arm_pos += 0.01;
	    left_arm_pos = (left_arm_pos > 1.0 ? 1.0 : left_arm_pos);
	} else if(gamepad2.left_trigger != 0.0){
	    record(3, 0.0, 1.0);
	    recorded = true;
	    left_arm_pos -= 0.01;
	    left_arm_pos = (left_arm_pos < 0.0 ? 0.0 : left_arm_pos);
	}
	if(gamepad2.right_bumper){
	    record(4, 1.0, 0.0);
	    recorded = true;
	    right_arm_pos -= 0.01;
	    right_arm_pos = (right_arm_pos < 0.0 ? 0.0 : right_arm_pos);
	} else if(gamepad2.right_trigger != 0.0){
	    record(4, 0.0, 1.0);
	    recorded = true;
	    right_arm_pos += 0.01;
	    right_arm_pos = (right_arm_pos > 1.0 ? 1.0 : right_arm_pos);
	}
		
	// update stuff
	gamepad1_x_pressed = gamepad1.x;
	gamepad1_b_pressed = gamepad1.b;

	left_arm.setPosition(left_arm_pos);
	right_arm.setPosition(right_arm_pos);

	if(!recorded){
	    record(0, 0.0, 0.0);
	    left_drive.setPower(0.0);
	    right_drive.setPower(0.0);
	}
    }
    public void init_loop(){

    }
    public void init(){
	super.init();
    }

    public void stop(){
	record(0, 0.0, 0.0);	
	try{
	    writeFile();
	} catch(Exception e){
	    telemetry.addData("03", e.toString());
	}
    }
}
