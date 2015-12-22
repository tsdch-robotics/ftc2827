package com.qualcomm.ftcrobotcontroller.opmodes;

public class TestRunOpMode extends TestBotHardware {

    boolean running = true;
    boolean ioerror = false;
    String ioer;
    public TestRunOpMode(){
	super();
	telemetry.addData("02", "constructor starting ...");
	setName("test.rec");
	try{
	    loadFile();
	} catch(Exception e){
	    ioerror = true;
	    ioer = e.toString();
	}
	telemetry.addData("02", "constructor done");
    }

    public void loop(){
	telemetry.addData("02", "loop starting ... ");
	telemetry.addData("01", "size :" + getListSize());
	if(ioerror){
	    telemetry.addData("02", "ioerror: " + ioer);
	}else if(running){
	    try{
		nextNode();
		telemetry.addData("01", "command: " + current.command + " " + nodes);
		if(current.command >= 0 && current.command <= 6){
		    left_motor.setPower(current.lvalue);
		    right_motor.setPower(current.rvalue);
		}
	    } catch (IndexOutOfBoundsException e){
		running = false;
	    }
	} else {
	    telemetry.addData("02", "RecRunDone");
	}
    }
}
