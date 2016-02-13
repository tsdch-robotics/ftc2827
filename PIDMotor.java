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
    int prev_p_error = 0;
    int p_i_error = 0;

    double speed = 0;
    double current_speed = 0;
    int last_clicks = 0;
    double prev_s_error = 0.0;
    double s_i_error = 0.0;

    double power = 0;
    int position = 0;
    boolean position_reached = true;

    final int MAX_CLICKS_PER_UPDATE = 20;

    public PIDMotor(DcMotor _motor) {
	motor = _motor;
    }

    public void setPower(double _power) {
	power = _power;
    }

    public void setPosition(int target) {
	position_reached = false;
	position = target;
	mode = Mode.POSITION;
	current_speed = 0;
    }

    double old_target = 0.0;

    public void setSpeed(double target) {
	if (speed != target) {
	    speed = target;
	    current_speed = target;
	    s_i_error = 0;
	}
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

    public int getCurrentPosition() {
	return motor.getCurrentPosition();
    }

    public double getCurrentSpeed() {
	return (motor.getCurrentPosition()-last_clicks)/MAX_CLICKS_PER_UPDATE;
    }

    public void update() {

	double kP, kI, kD;
	boolean should_run = true;
	final int DEAD_POS = 50;
	final double DEAD_SPEED = 0.01;
	
	if (reset_encoder) {
	    motor.setPower(0);
	    setDriveMode(DcMotorController.RunMode.RESET_ENCODERS);
	    if (getCurrentPosition() == 0){
		reset_encoder = false;
	    }
	} else {

	    setDriveMode(DcMotorController.RunMode.RUN_WITHOUT_ENCODERS);

	    switch(mode) {
	    case POSITION:

		kP = 0.0007;
		kI = 0.00001;
		kD = 0.0;

		int p_error = position - motor.getCurrentPosition();
		should_run = Math.abs(p_error) > DEAD_POS;
		if (position_reached && should_run) {
		    p_i_error += p_error;
		} else {
		    p_i_error = 0;
		}

		double p_deriv = p_error - prev_p_error;
		prev_p_error = p_error;

		double motor_power = power*(kP*p_error + kI*p_i_error + kD*p_deriv);
		motor_power = Range.clip(motor_power,-0.99999999,0.999999999);
		if (should_run) {
		    motor.setPower(motor_power);
		} else {
		    position_reached = true;
		    motor.setPower(0);
		}
		break;

	    case SPEED:

		kP = 0.1;
		kI = 0.0;
		kD = -0.005;

		double s_error = speed - getCurrentSpeed();
		should_run = Math.abs(s_error) > DEAD_SPEED;
		if(should_run) {

		    s_i_error += s_error;

		    double s_deriv = s_error - prev_s_error;
		    prev_s_error = s_error;

		    current_speed += kP*s_error + kI*s_i_error + kD*s_deriv;
		    current_speed = Range.clip(current_speed, -0.99999999,0.999999999);
		    motor.setPower(current_speed);
		} else {
		    motor.setPower(0.0);
		}
	    }
	}
	last_clicks = motor.getCurrentPosition();
    }

}
