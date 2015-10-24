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
	current = new RecRunNode(0, 0.0, 0);
	
    }

    public void setThreshold(double threshold){
	this.threshold = threshold;
    }

    public void record(int command, double value){
	if(abs(current.value - value) > threshold || command != current.command){
	    recrun.push(new RecRunNode(current.command,
				       current.value,
				       System.nanoTime - current.duration));
	    current.command = command;
	    current.value = value;
	    current.duration = System.nanoTime();
	}
    }

    public RecRunNode nextNode() throws RecRunDoneException{
	if(startTime + current.duration > System.nanoTime()){
	    current = recrun.getNxt();
	}
	return current;
    }
}
