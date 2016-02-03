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

public class TestAutonomous extends TestHardware {

    double motor_power = 0.0;
    int num_targets_set = 0;
    boolean target_reached = true;
    int left_target = 0;
    int right_target = 0;
    boolean right_running = false;
    boolean left_running = false;
    boolean reset_encoders = false;

    public TestAutonomous() {

    }

    public void resetEncoders() {
	reset_encoders = true;
    }

    public void setTarget(int left, int right) {
	left_target = left;
	right_target = right;
	right_running = true;
	left_running = true;
	++num_targets_set;
	target_reached = false;
	resetEncoders();
    }

    public void runMotors() {

	int ERROR_MARGIN = 25;
	double kP = 0.0005;

	if (reset_encoders) {
	    setDriveMode(DcMotorController.RunMode.RESET_ENCODERS);
	    if (left_drive.getCurrentPosition() == 0
		&& right_drive.getCurrentPosition() == 0) {
		reset_encoders = false;
	    }
	} else {

	    setDriveMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);
	
	    if (left_running || right_running) {
	    
		int left_error = left_target - left_drive.getCurrentPosition();
		int right_error = right_target - right_drive.getCurrentPosition();
	    
		telemetry.addData("left error", left_error);
		telemetry.addData("right error", right_error);

		if (left_running 
		    && Math.abs(left_error) > ERROR_MARGIN) {
		    double left_power = Range.clip(motor_power*kP*left_error,-1.0,1.0);
		    telemetry.addData("left power", left_power);
		    left_drive.setPower(left_power);
		} else {
		    left_running = false;
		    left_drive.setPower(0);
		    if (!right_running) {
			target_reached = true;
		    }
		}

		if (right_running &&
		    Math.abs(right_error) > ERROR_MARGIN) {
		    double right_power = Range.clip(motor_power*kP*right_error,-1.0,1.0);
		    telemetry.addData("right power", right_power);
		    right_drive.setPower(right_power);
		} else {
		    right_running = false;
		    right_drive.setPower(0);
		    if (!right_running) {
			target_reached = true;
		    }
		}
	    } else {
		left_drive.setPower(0);
		right_drive.setPower(0);
	    }
	}
    }

    public void loop() {

	telemetry.addData("target reached", target_reached);
	telemetry.addData("targets set", num_targets_set);
	telemetry.addData("left encoder", left_drive.getCurrentPosition());
	telemetry.addData("right encoder", right_drive.getCurrentPosition());

	if (target_reached) {
	    switch (num_targets_set) {
	    case 0:
		motor_power = 1.0;
		setTarget(1000,1000);
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
	target_reached = true;
	num_targets_set = 0;
	resetEncoders();
    }
}
