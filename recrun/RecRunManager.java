package com.qualcomm.ftcrobotcontroller.opmodes.recrun;

import java.util.*;
import java.io.*;
import android.os.Environment;

public class RecRunManager
{
    List list;
    int index;
    String name;
    private static RecRunManager instance = null;
    private RecRunManager(){
	list = new ArrayList<RecRunNode>();
	index = 0;
	Date date = new Date();
	name = date.toString();
    }

    public static RecRunManager getManager(){
	if(instance == null){
	    instance = new RecRunManager();
	}
	return instance;
    }
    public void push(RecRunNode record){
	list.add(index, record);
	index++;
    }
    public RecRunNode getNxt() throws RecRunDoneException{
	if(index > list.size()){
	    RecRunNode ret = (RecRunNode) list.remove(index);
	    index++;
	    return ret;
	} else {
	    throw new RecRunDoneException();
	}
    }
    
    public void setFileName(String name){
	this.name = name;
    }
    File getFile(){
	File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), name);

	return file;
    }
    
    void writeFile(){
	try {
	    FileOutputStream fos = new FileOutputStream(this.getFile());
	    ObjectOutputStream oos = new ObjectOutputStream(fos);
	    oos.writeObject(list);

	    oos.close();
	} catch(IOException e){
	    //do important stuff
	}
    }
    
    void readFile(){
	try{
	    FileInputStream fis = new FileInputStream(this.getFile());
	    ObjectInputStream ois = new ObjectInputStream(fis);
	    list = (ArrayList<RecRunNode>) ois.readObject();

	    ois.close();
	} catch(IOException e){
	    //do more important stuff
	} catch(Exception o){
	    //OBJECT NOT FOUND
	}
    }
}

