package com.qualcomm.ftcrobotcontroller.opmodes.recrun;

import java.io.*;

public class RecRunNode implements Serializable
{
    public int command;
    public double rvalue;
    public double lvalue;
    public long duration;

    public RecRunNode(int command, double lvalue, double rvalue, long duration){
	this.command = command;
	this.rvalue = rvalue;
	this.lvalue = lvalue;
	this.duration = duration;
    }
}
