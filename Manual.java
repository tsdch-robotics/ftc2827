package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.Range;

public class Manual extends Hardware {

    boolean gamepad1_x_pressed = false;
    boolean gamepad1_b_pressed = false;

    public Manual(){

    }

    public void loop() {

	float r_power = gamepad1.left_stick_y;
	float l_power = gamepad1.right_stick_y;

	l_power = Range.clip(l_power, -1, 1);
	r_power = Range.clip(r_power, -1, 1);

	left_drive.setPower(r_power);
	right_drive.setPower(l_power);

	if (!gamepad1_x_pressed && gamepad1.x) {
	    toggleLeftClimber();
	}

	if (!gamepad1_b_pressed && gamepad1.b) {
	    toggleRightClimber();
	}

	gamepad1_x_pressed = gamepad1.x;
	gamepad1_b_pressed = gamepad1.b;

	telemetry.addData("lp", "Left Power" + l_power);
	telemetry.addData("rp", "Right Power" + r_power);
    }
    public void init(){
	super.init();
	left_climber.setPosition(0.0);
	right_climber.setPosition(0.0);
    }
}
