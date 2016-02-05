package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

public class PIDMotor {

    public enum Mode {
	SPEED, POSITION
    }

    Mode mode = Mode.POSITION;
    DcMotor motor;
    boolean reset_encoder = true;

    double speed = 0;
    double current_speed = 0;
    int last_clicks = 0;

    double power = 0;
    int position = 0;
    boolean position_reached = true;

    final int MAX_CLICKS_PER_UPDATE = 50;

    public PIDMotor(DcMotor _motor) {
	motor = _motor;
    }

    public void setPower(double _power) {
	power = _power;
    }

    public void setPosition(int target) {
	position = target;
	position_reached = false;
	mode = Mode.POSITION;
	current_speed = 0;
    }

    public void setSpeed(double target) {
	speed = target;
	current_speed = target;
	mode = Mode.SPEED;
    }

    public boolean positionReached() {
	return position_reached && (mode == Mode.POSITION);
    }

    public void resetEncoder() {
	reset_encoder = true;
    }

    public void setDriveMode(DcMotorController.RunMode mode) {
        if (motor.getChannelMode() != mode) {
            motor.setChannelMode(mode);
        }
    }

    public double getCurrentSpeed() {
	return (motor.getCurrentPosition()-last_clicks);
    }

    public void update() {

	double min_power = 0.1;
	double kP, kI, kD;
	
	if (reset_encoder) {
	    motor.setPower(0);
	    setDriveMode(DcMotorController.RunMode.RESET_ENCODERS);
	    if (motor.getCurrentPosition() == 0) {
		reset_encoder = false;
	    }
	} else {

	    setDriveMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

	    switch(mode) {
	    case POSITION:
		kP = 0.0007;
		int p_error = position - motor.getCurrentPosition();
		double motor_power = Range.clip(power*kP*p_error,-1.0,1.0);
		if (Math.abs(motor_power) > min_power) {
		    motor.setPower(motor_power);
		} else {
		    position_reached = true;
		    motor.setPower(0);
		}
		break;
	    case SPEED:
		kP = 0.001;
		double s_error = speed - getCurrentSpeed();
		current_speed += kP*s_error;
		motor.setPower(current_speed);
	    }
	}
	last_clicks = motor.getCurrentPosition();
    }

}
