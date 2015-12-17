package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import java.io.*;
import android.os.Environment;

public class RecRunChoose extends OpMode{

    File dir;
    File chosen;
    File[] files;
    String[] names;
    int num;
    int index;
    boolean choosing;
    boolean ischosen = false;
    boolean upressed;
    boolean dpressed;

    public RecRunChoose(){
	dir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
	chosen = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "chosen.txt");
	chosen.delete();
	files = dir.listFiles();
	names = new String[files.length];
	for(int i = 0; i < files.length; i++){
	    names[i] = files[i].getName();
	}
	num = names.length;
	index = 0;
        choosing = true;
	upressed = false;
        dpressed = false;
    }
    
    public void loop(){

	telemetry.addData("01", names[index]);
	if(ischosen){
	    telemetry.addData("02", "Chosen!");
	} else {
	    telemetry.addData("02", "Choosing...");
	}
	if(gamepad1.dpad_up && !upressed){
	    index++;
	    index %= num;
	} else if(gamepad1.dpad_down && !dpressed){
	    index--;
	    index += num;
	    index %= num;
	} else if(gamepad1.a){
	    try{
		ischosen = true;
		PrintWriter pw = new PrintWriter(chosen);
		pw.println(names[index]);
		pw.close();
	    } catch(Exception e){}
	}
	upressed = gamepad1.dpad_up;
	dpressed = gamepad1.dpad_down;
	}
    public void init(){}
}
