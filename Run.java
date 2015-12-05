package com.qualcomm.ftcrobotcontroller.opmodes;
import com.qualcomm.ftcrobotcontroller.opmodes.recrun.*;

public class Run extends RecRunOpMode {

    boolean running = true;
    boolean ioerror = false;
    String ioer;
    public Run(){
	super();
	setName("test.rec");
	try{
	    loadFile();
	} catch(Exception e){
	    ioerror = true;
	    ioer = e.toString();
	}
    }

    public void loop(){
	//telemetry.addData("03", "size :" + getListSize());
	if(ioerror){
	    telemetry.addData("03", "ioerror: " + ioer);
	}else if(running){
	    try{
		nextNode();
		telemetry.addData("02", "command: " + current.command + " " + current.duration);
		if(current.command >= 0 && current.command <= 6){
		    telemetry.addData("01", "l_v: " + current.lvalue + "\tr_v: " + current.rvalue);
		}
	    } catch (IndexOutOfBoundsException e){
		running = false;
	    }
	} else {
	    telemetry.addData("02", "RecRunDone");
	}
    }
}
