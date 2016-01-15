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

    boolean running = true;

    public Autonomous() {

    }

    public void resetEncoders() {
	left_drive.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
	right_drive.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
    }

    public void drive(double power, 
		      int left_target, 
		      int right_target) {

	boolean left_running = true;
	boolean right_running = true;
	int ERROR_MARGIN = 10;
	double kP = 0.1;

	resetEncoders();

	while (left_running && right_running) {

	    int left_error = left_target - left_drive.getCurrentPosition();
	    int right_error = right_target - right_drive.getCurrentPosition();

	    telemetry.addData("left encoder", left_drive.getCurrentPosition());

	    if (Math.abs(left_error) > ERROR_MARGIN) {
		left_drive.setPower(Range.clip(power*kP*left_error,-1.0,1.0));
	    } else {
		left_running = false;
	    }

	    if (Math.abs(right_error) > ERROR_MARGIN) {
		right_drive.setPower(Range.clip(power*kP*left_error,-1.0,1.0));
	    } else {
		right_running = false;
	    }
	}

	left_drive.setPower(0);
	right_drive.setPower(0);
    }

    public void loop() {
	telemetry.addData("01", "Test");
	if (running) {
	    drive(1,1000,1000);
	    running = false;
	}
    }

    public void init_loop(){

    }
    public void init(){
	super.init();
    }
}
