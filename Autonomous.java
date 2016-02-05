package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/*
  get encoder position:
  motor.getCurrentPosition();
  reset encoder position:
  motor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
 */

public class Autonomous extends Hardware {

    int num_targets_set = 0;
    boolean target_reached = true;
    PIDMotor left;
    PIDMotor right;
    

    public Autonomous() {}

    public void resetEncoders() {
        left.resetEncoder();
	right.resetEncoder();
    }

    public void setPower(double power) {
	left.setPower(power);
	right.setPower(power);
    }

    public void setTarget(int left_targ, int right_targ) {
	++num_targets_set;
	target_reached = false;
	left.setPosition(-left_targ);
	right.setPosition(-right_targ);
	resetEncoders();
    }

    public void runMotors() {
	left.update();
	right.update();
	target_reached = left.positionReached() && right.positionReached();
    }

    public void loop() {

	telemetry.addData("target reached", target_reached);
	telemetry.addData("targets set", num_targets_set);
	telemetry.addData("left encoder", left_drive.getCurrentPosition());
	telemetry.addData("right encoder", right_drive.getCurrentPosition());

	if (target_reached) {
	    switch (num_targets_set) {
	    case 0:
		setPower(1.0);
		setTarget(1000,1000);
		break;
	    case 1:
		setTarget(1000,-1000);
		break;
	    }
	}
	runMotors();
    }

    public void init_loop() {
	target_reached = true;
	num_targets_set = 0;
	resetEncoders();
    }

    public void init() {
	super.init();
	left = new PIDMotor(left_drive);
	right = new PIDMotor(right_drive);
	target_reached = true;
	num_targets_set = 0;
	resetEncoders();
    }
}
