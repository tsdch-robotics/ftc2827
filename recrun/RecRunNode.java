package com.qualcomm.ftcrobotcontroller.opmodes.recrun;

import java.io.*;

public class RecRunNode implements Serializable
{
    int command;
    double rvalue;
    double lvalue;
    long duration;

    public RecRunNode(int command, double lvalue, double rvalue, long duration){
	this.command = command;
	this.rvalue = rvalue;
	this.lvalue = lvalue;
	this.duration = duration;
    }
}
