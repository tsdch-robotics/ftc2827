package com.qualcomm.ftcrobotcontroller.opmodes.recrun;

import java.util.*;
import java.io.*;
import android.os.Environment;

public class RecRunManager
{
    List<RecRunNode> list;
    int index;
    String name;
    private static RecRunManager instance = null;
    private RecRunManager(){
	list = new ArrayList<RecRunNode>();
	index = 0;
	Date date = new Date();
	name = date.toString();
	name += ".rec";
    }
    
    public int getArrayLength(){
	return list.size();
    }

    public void reRec(){
	index = 0;
	list = new ArrayList<RecRunNode>();
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
    public RecRunNode getNxt() throws IndexOutOfBoundsException{
	try{
	    RecRunNode ret = list.get(index);
	    index++;
	    return ret;
	} catch(IndexOutOfBoundsException e){
	    index = 0;
	    throw e;
	}
    }
    
    public void setFileName(String name){
	this.name = name;
    }
    File getFile(){
	File file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), name);

	return file;
    }
    
    void writeFile() throws Exception{
	File file = this.getFile();
	file.delete();
	FileOutputStream fos = new FileOutputStream(file);
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	oos.writeObject(list);
	oos.close();
	index = 0;
    }
    
    void readFile() throws Exception{
	FileInputStream fis = new FileInputStream(this.getFile());
	ObjectInputStream ois = new ObjectInputStream(fis);
	list = (ArrayList<RecRunNode>) ois.readObject();

	ois.close();
    }
}

