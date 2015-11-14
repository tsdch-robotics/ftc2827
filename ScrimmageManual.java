package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.util.Range;

public class ScrimmageManual extends ScrimmageHardware {

    public ScrimmageManual() {

    }

    public void loop() {

	float l_power = gamepad1.left_stick_y;
	float r_power = gamepad1.right_stick_y;

	l_power = Range.clip(l_power, -1, 1);
	r_power = Range.clip(r_power, -1, 1);

	left_drive.setPower(l_power);
	right_drive.setPower(r_power);
	
	telemetry.addData("lp", "Left Power" + l_power);
	telemetry.addData("rp", "Right Power" + r_power);
    }
}
