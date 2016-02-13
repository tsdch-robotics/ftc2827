package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.Range;

public class Manual extends Hardware {

    PIDMotor left_arm_pid;
    PIDMotor right_arm_pid;
    int arm_target = 1000;
    double arm_speed = 0.1;

    boolean gamepad1_x_pressed = false;
    boolean gamepad1_b_pressed = false;

    double left_plow_position = 1.0;
    double right_plow_position = 0.0;

    public Manual() {

    }

    public void loop() {

	//driving
	double r_power = -gamepad1.left_stick_y;
	double l_power = -gamepad1.right_stick_y;
	l_power = Range.clip(l_power, -0.99999999,0.999999999);
	r_power = Range.clip(r_power, -0.99999999,0.999999999);
	left_drive.setPower(r_power);
	right_drive.setPower(l_power);

	//climbers
	if (!gamepad1_x_pressed && gamepad1.left_bumper) {
	    toggleLeftClimber();
	}
	if (!gamepad1_b_pressed && gamepad1.right_bumper) {
	    toggleRightClimber();
	}

	//plow
	if (gamepad1.y) {
	    right_plow_position -= 0.01;
	    left_plow_position += 0.01;
	    right_plow_position = right_plow_position > 0.0 ? right_plow_position : 0.0;
	    left_plow_position = left_plow_position < 1.0 ? left_plow_position : 1.0;	    
	} else if (gamepad1.a) {
	    right_plow.setPosition(1.0);
	    left_plow.setPosition(0.0);
	    right_plow_position += 0.01;
	    left_plow_position -= 0.01;
	    right_plow_position = right_plow_position < 1.0 ? right_plow_position : 1.0;
	    left_plow_position = left_plow_position > 0.0 ? left_plow_position : 0.0;	    
	}

	//arms
	if (gamepad2.b) {
	    right_arm_pid.setPosition(arm_target);
	    left_arm_pid.setPosition(arm_target);   
	}else{
	    if (gamepad2.right_bumper || gamepad2.left_bumper) {
		right_arm_pid.setSpeed(arm_speed);
		left_arm_pid.setSpeed(arm_speed);
	    } else if (gamepad2.right_trigger != 0.0 ||
		       gamepad2.left_trigger != 0.0) {
		right_arm_pid.setSpeed(-arm_speed);
		left_arm_pid.setSpeed(-arm_speed);
	    } else if (right_arm_pid.mode == PIDMotor.Mode.SPEED
		       && left_arm_pid.mode == PIDMotor.Mode.SPEED) {
		right_arm_pid.setPosition(right_arm_pid.getCurrentPosition());
		left_arm_pid.setPosition(left_arm_pid.getCurrentPosition());
	    }
	}
	//pullup
	if(gamepad2.y){
	    left_pullup.setPower(upPower);
	    right_pullup.setPower(upPower);
	} else if(gamepad2.a){
	    left_pullup.setPower(downPower);
	    right_pullup.setPower(downPower);
	} else if(gamepad1.dpad_up){
	    left_pullup.setPower(upPower);
	    right_pullup.setPower(upPower);
	} else if(gamepad1.dpad_down){
	    left_pullup.setPower(downPower);
	    right_pullup.setPower(downPower);
	} else if (gamepad2.left_stick_y > 0.25
	    && gamepad2.right_stick_y > 0.25) {
	    left_pullup.setPower(-0.5);
	    right_pullup.setPower(-0.5);
	} else if (gamepad2.left_stick_y < -0.25
		   && gamepad2.right_stick_y < -0.25) {
	    left_pullup.setPower(0.5);
	    right_pullup.setPower(0.5);
	} else {
	    left_pullup.setPower(0);
	    right_pullup.setPower(0);
	}	    

	//hooks
	left_hook.setPosition(0.5 * (gamepad2.left_stick_y + 1.0));
	right_hook.setPosition(0.5 * (-gamepad2.right_stick_y + 1.0));
	
	
	// update stuff
	gamepad1_x_pressed = gamepad1.left_bumper;
	gamepad1_b_pressed = gamepad1.right_bumper;

	left_plow.setPosition(left_plow_position);
	right_plow.setPosition(right_plow_position);
	
	telemetry.addData("md", "Mode" + right_arm_pid.mode);
	telemetry.addData("pr", "Position reached" + right_arm_pid.position_reached);
	telemetry.addData("ie", "I error" + right_arm_pid.p_i_error);
	telemetry.addData("lp", "Left Power" + l_power);
	telemetry.addData("rp", "Right Power" + r_power);
	telemetry.addData("sv", //left_climber.getPosition() + " "
			  //+ right_climber.getPosition() + " "
			  left_arm.getPower() + " "
			  + right_arm.getPower() + " ");

	right_arm_pid.update();
	left_arm_pid.update();
    }

    public void init_loop(){
	right_arm_pid.resetEncoder();
	left_arm_pid.resetEncoder();
    }

    public void init(){
	super.init();
	right_arm_pid = new PIDMotor(right_arm);
	right_arm_pid.setPower(1.0);
	left_arm_pid = new PIDMotor(left_arm);
	left_arm_pid.setPower(1.0);
    }
}
