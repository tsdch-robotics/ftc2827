package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.Range;

public class Manual extends Hardware {

    boolean gamepad1_x_pressed = false;
    boolean gamepad1_b_pressed = false;

    public Manual(){

    }

    public void loop() {

	//driving
	float r_power = gamepad1.left_stick_y;
	float l_power = gamepad1.right_stick_y;
	l_power = Range.clip(l_power, -1, 1);
	r_power = Range.clip(r_power, -1, 1);
	left_drive.setPower(r_power);
	right_drive.setPower(l_power);

	//climbers
	if (!gamepad1_x_pressed && gamepad1.left_bumper) {
	    toggleLeftClimber();
	}
	if (!gamepad1_b_pressed && gamepad1.right_bumper) {
	    toggleRightClimber();
	}
	
	//arms
	if(gamepad2.left_bumper){
	    left_arm_pos += 0.005;
	    left_arm_pos = (left_arm_pos > 1.0 ? 1.0 : left_arm_pos);
	} else if(gamepad2.left_trigger != 0.0){
	    left_arm_pos -= 0.005;
	    left_arm_pos = (left_arm_pos < 0.0 ? 0.0 : left_arm_pos);
	}
	if(gamepad2.right_bumper){
	    right_arm_pos -= 0.005;
	    right_arm_pos = (right_arm_pos < 0.0 ? 0.0 : right_arm_pos);
	} else if(gamepad2.right_trigger != 0.0){
	    right_arm_pos += 0.005;
	    right_arm_pos = (right_arm_pos > 1.0 ? 1.0 : right_arm_pos);
	}

	//pullup
	if(gamepad2.y){
	    left_pullup.setPower(upPower);
	    right_pullup.setPower(upPower);
	} else if(gamepad2.a){
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
	gamepad1_x_pressed = gamepad1.left_bumper;
	gamepad1_b_pressed = gamepad1.right_bumper;

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
