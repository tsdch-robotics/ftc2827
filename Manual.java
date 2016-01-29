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

	//plow
	if (gamepad1.y) {
	    right_plow.setPosition(0.0);
	    left_plow.setPosition(1.0);
	} else if (gamepad1.a) {
	    right_plow.setPosition(1.0);
	    left_plow.setPosition(0.0);
	}
	
	final double ARM_POWER_UP = 0.3;
	final double ARM_POWER_DOWN = -0.15;
	double left_arm_power = 0;
	double right_arm_power = 0;

	//arms
	if (gamepad2.left_bumper) {
	    left_arm_power = ARM_POWER_UP;
	} else if (gamepad2.left_trigger != 0.0) {
	    left_arm_power = ARM_POWER_DOWN;
	}

	if (gamepad2.right_bumper) {
	    right_arm_power = ARM_POWER_UP;
	} else if (gamepad2.right_trigger != 0.0) {
	    right_arm_power = ARM_POWER_DOWN;
	}
	
	right_arm.setPower(right_arm_power);
	left_arm.setPower(left_arm_power);
	//pullup
	if(gamepad2.y){
	    left_pullup.setPower(upPower);
	    right_pullup.setPower(upPower);
	} else if(gamepad2.a){
	    left_pullup.setPower(downPower);
	    right_pullup.setPower(downPower);
	} else if (Math.abs(gamepad2.left_stick_y) > 0.1
		   && Math.abs(gamepad2.right_stick_y) > 0.1) {
	    left_pullup.setPower(-0.5*gamepad2.right_stick_y);
	    right_pullup.setPower(-0.5*gamepad2.right_stick_y);
	} else {
	    left_pullup.setPower(0);
	    right_pullup.setPower(0);
	}

	//hooks
	left_hook.setPosition(0.5 * (-gamepad2.left_stick_y + 1.0));
	right_hook.setPosition(0.5 * (gamepad2.right_stick_y + 1.0));
	
	
	
	// update stuff
	gamepad1_x_pressed = gamepad1.left_bumper;
	gamepad1_b_pressed = gamepad1.right_bumper;

	telemetry.addData("lp", "Left Power" + l_power);
	telemetry.addData("rp", "Right Power" + r_power);
	telemetry.addData("sv", //left_climber.getPosition() + " "
			  //+ right_climber.getPosition() + " "
			  left_arm.getPower() + " "
			  + right_arm.getPower() + " ");
			  // + left_hook.getPosition() + " "
			  // + right_hook.getPosition());
    }
    public void init_loop(){

    }
    public void init(){
	super.init();
    }
}
