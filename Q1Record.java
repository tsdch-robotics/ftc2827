package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.recrun.*;
import com.qualcomm.robotcore.util.Range;

public class Q1Record extends Manual {

    boolean gamepad1_x_pressed = false;
    boolean gamepad1_b_pressed = false;
    double deadzone = 0.01;

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
	}

	//climbers
	if (!gamepad1_x_pressed && gamepad1.x) {
	    record(2, 1.0, 0.0);
	    toggleLeftClimber();
	}
	if (!gamepad1_b_pressed && gamepad1.b) {
	    record(2, 0.0, 1.0);
	    toggleRightClimber();
	}
	
	//arms
	if(gamepad2.left_bumper){
	    record(3, 1.0, 0.0);
	    left_arm_pos += 0.01;
	    left_arm_pos = (left_arm_pos > 1.0 ? 1.0 : left_arm_pos);
	} else if(gamepad2.left_trigger != 0.0){
	    record(3, 0.0, 1.0);
	    left_arm_pos -= 0.01;
	    left_arm_pos = (left_arm_pos < 0.0 ? 0.0 : left_arm_pos);
	}
	if(gamepad2.right_bumper){
	    record(4, 1.0, 0.0);
	    right_arm_pos -= 0.01;
	    right_arm_pos = (right_arm_pos < 0.0 ? 0.0 : right_arm_pos);
	} else if(gamepad2.right_trigger != 0.0){
	    record(4, 0.0, 1.0);
	    right_arm_pos += 0.01;
	    right_arm_pos = (right_arm_pos > 1.0 ? 1.0 : right_arm_pos);
	}
	

	//pullup
	if(gamepad1.y){
	    left_pullup.setPower(upPower);
	    right_pullup.setPower(upPower);
	} else if(gamepad1.a){
	    left_pullup.setPower(downPower);
	    right_pullup.setPower(downPower);
	} else {
	    left_pullup.setPower(0.0);
	    right_pullup.setPower(0.0);
	}

	//hooks
	left_hook.setPosition(0.5 * (-gamepad2.left_stick_y + 1.0));
	right_hook.setPosition(0.5 * (gamepad2.right_stick_y + 1.0));
	
	// update stuff
	gamepad1_x_pressed = gamepad1.x;
	gamepad1_b_pressed = gamepad1.b;

	left_arm.setPosition(left_arm_pos);
	right_arm.setPosition(right_arm_pos);

	telemetry.addData("lp", "Left Power" + l_power);
	telemetry.addData("rp", "Right Power" + r_power);
	telemetry.addData("sv", //left_climber.getPosition() + " "
			  //+ right_climber.getPosition() + " "
			  left_arm.getPosition() + " "
			  + right_arm.getPosition() + " ");
			  // + left_hook.getPosition() + " "
			  // + right_hook.getPosition());
    }
    public void init_loop(){

    }
    public void init(){
	super.init();
    }
}
