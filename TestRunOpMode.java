package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.recrun.RecRunDoneException;

public class TestRunOpMode extends TestBotHardware {

    boolean running;
    public TestRunOpMode(){
	super();
	telemetry.addData("02", "constructor starting ...");
	running = true;
	try{
	    loadFile();
	} catch(Exception e){
	    telemetry.addData("03", "exe: " + e.toString());
	}
	setName("test.rec");

	telemetry.addData("02", "constructor done");
    }

    public void loop(){
	while(running){
	    try{
		nextNode();

		if(current.command >= 0 && current.command <= 6){
		    left_motor.setPower(current.lvalue);
		    right_motor.setPower(current.rvalue);
		}
	    } catch (RecRunDoneException e){
		running = false;
	    }
	}
    }
}
