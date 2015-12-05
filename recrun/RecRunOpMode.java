package com.qualcomm.ftcrobotcontroller.opmodes.recrun;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.lang.Math.*;

public class RecRunOpMode extends OpMode {

    private RecRunManager recrun;
    private double threshold = 0.1;
    private long startTime = 0;
    protected RecRunNode current;
    protected int nodes = 0;

    public RecRunOpMode(){
	super();
	recrun = RecRunManager.getManager();
	recrun.reRec();
	current = new RecRunNode(0, 0.0, 0.0, 0);
	startTime = System.nanoTime();
	
    }

    public void setThreshold(double threshold){
	this.threshold = threshold;
    }

    public void setName(String name){
	recrun.setFileName(name);
    }

    public void record(int command, double lvalue, double rvalue){
	if((Math.abs(current.rvalue - rvalue) > threshold) || 
	   (Math.abs(current.lvalue - lvalue) > threshold) ||
	   command != current.command){

	   recrun.push(new RecRunNode(current.command,
				       current.lvalue,
				       current.rvalue,
				       System.nanoTime() - startTime));
	    current.command = command;
	    current.rvalue = rvalue;
	    current.lvalue = lvalue;
	    startTime = System.nanoTime();
	}
    }

    public void nextNode() throws IndexOutOfBoundsException{
	if(startTime + current.duration < System.nanoTime()){
	    current = recrun.getNxt();
	    startTime = System.nanoTime();
	    nodes++;
	}
    }

    public int getListSize(){
	return recrun.getArrayLength();
    }

    public void loadFile() throws Exception{
	recrun.readFile();
    }

    public void writeFile() throws Exception{
	recrun.writeFile();
    }

    public void loop(){} // Java sux
    public void init(){}
}
