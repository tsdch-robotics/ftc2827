package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.ftcrobotcontroller.opmodes.recrun.*;
import com.qualcomm.robotcore.util.Range;
import java.io.*;
import android.os.Environment;

public class Q1Run extends Hardware{

    boolean running = true;
    boolean ioerror = false;
    String ioer;
    public Q1Run(){
	super();
	File chosen = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "chosen.txt");
	String name = "";
	try{
	    InputStream fs = new FileInputStream(chosen);
	    InputStreamReader isr = new InputStreamReader(fs);
	    BufferedReader br = new BufferedReader(isr);
	    name = br.readLine();
	    fs.close();
	} catch(Exception e){

	}
	setName(name);
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
		switch(current.command){
		case 0:
		    left_drive.setPower(0.0);
		    right_drive.setPower(0.0);
		    break;
		case 1:
		    left_drive.setPower(current.lvalue);
		    right_drive.setPower(current.rvalue);
		    break;
		case 2:
		    if(current.lvalue == 1.0){
			toggleLeftClimber();
		    } else {
			toggleRightClimber();
		    }
		    break;
		case 3:
		    if(current.lvalue == 1.0){
			left_arm_pos += 0.01;
			left_arm_pos = (left_arm_pos > 1.0 ? 1.0 : left_arm_pos);
		    } else {
			left_arm_pos -= 0.01;
			left_arm_pos = (left_arm_pos < 0.0 ? 0.0 : left_arm_pos);
		    }
		    break;
		case 4:
		    if(current.lvalue == 1.0){
			right_arm_pos -= 0.01;
			right_arm_pos = (right_arm_pos < 0.0 ? 0.0 : right_arm_pos);
		    } else {
			right_arm_pos += 0.01;
			right_arm_pos = (right_arm_pos > 1.0 ? 1.0 : right_arm_pos);
		    }
		    break;
		}

	    } catch (IndexOutOfBoundsException e){
		running = false;
	    }
	} else {
	    telemetry.addData("02", "RecRunDone");
	}
    }
    public void stop(){
	super.stop();
	left_drive.setPower(0.0);
	right_drive.setPower(0.0);
    }
}

