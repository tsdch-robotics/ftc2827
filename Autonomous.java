package com.qualcomm.ftcrobotcontroller.opmodes;

import java.util.ArrayList;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.util.Range;

/*
  get encoder position:
  motor.getCurrentPosition();
  reset encoder position:
  motor.setMode(DcMotorController.RunMode.RESET_ENCODERS);
 */

public class Autonomous extends Hardware {

    double motor_power = 0.0;
    int num_targets_reached = 0;
    int left_target = 0;
    int right_target = 0;
    boolean right_running = false;
    boolean left_running = false;

    public Autonomous() {

    }

    public void resetEncoders() {
	left_drive.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
	right_drive.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    public void setTarget(int left, int right) {
	left_target = 0;
	right_target = 0;
	right_running = true;
	left_running = false;
    }

    public void runMotors() {

	int ERROR_MARGIN = 10;
	double kP = 0.1;

	resetEncoders();

	if (left_running && right_running) {
	    int left_error = left_target - left_drive.getCurrentPosition();
	    int right_error = right_target - right_drive.getCurrentPosition();

	    telemetry.addData("left encoder", left_drive.getCurrentPosition());

	    if (Math.abs(left_error) > ERROR_MARGIN) {
		left_drive.setPower(Range.clip(motor_power*kP*left_error,-1.0,1.0));
	    } else {
		left_running = false;
		if (!right_running) {
		    ++num_targets_reached;
		}
	    }

	    if (Math.abs(right_error) > ERROR_MARGIN) {
		right_drive.setPower(Range.clip(motor_power*kP*left_error,-1.0,1.0));
	    } else {
		right_running = false;
		if (!right_running) {
		    ++num_targets_reached;
		}
	    }
	} else {
	    left_drive.setPower(0);
	    right_drive.setPower(0);
	}
    }

    public void loop() {
	switch (num_targets_reached) {
	case 0:
	    motor_power = 1.0;
	    setTarget(1000,1000);
	    break;
	}
	runMotors();
    }

    public void init_loop(){

    }
    public void init(){
	super.init();
    }
}
