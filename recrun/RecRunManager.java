package com.qualcomm.ftcrobotcontroller.opmodes.recrun;

public class RecRunManager
{
    private static RecRunManager instance = null;
    private RecRunManager(){
    }

    public static getManager(){
	if(instance == null){
	    instance = new RecRunManager;
	}
	return instance;
    }
}

