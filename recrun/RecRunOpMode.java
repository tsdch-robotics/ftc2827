package com.qualcomm.ftcrobotcontroller.opmodes.recrun;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.lang.Math.*;

public class RecRunOpMode extends OpMode {

    private RecRunManager recrun;
    private double threshold = 0.1;
    private long startTime = 0;
    private RecRunNode current;

    public RecRunOpMode(){
	super();
	recrun = RecRunManager.getManager();
	current = new RecRunNode(0, 0.0, 0.0, 0);
	
    }

    public void setThreshold(double threshold){
	this.threshold = threshold;
    }

    public void record(int command, double lvalue, double rvalue){
	if(Math.abs(current.rvalue - rvalue) > threshold || 
	   Math.abs(current.lvalue - lvalue) > threshold || 
	   command != current.command){

	    recrun.push(new RecRunNode(current.command,
				       current.lvalue,
				       current.rvalue,
				       System.nanoTime() - current.duration));
	    current.command = command;
	    current.rvalue = rvalue;
	    current.lvalue = lvalue;
	    current.duration = System.nanoTime();
	}
    }

    public RecRunNode nextNode() throws RecRunDoneException{
	if(startTime + current.duration > System.nanoTime()){
	    current = recrun.getNxt();
	}
	return current;
    }

    public void loop(){} // Java sux
    public void init(){}
}
