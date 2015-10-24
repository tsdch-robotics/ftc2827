package com.qualcomm.ftcrobotcontroller.opmodes.recrun;

import java.util.*
import java.io.*

public class RecRunManager
{
    List list;
    int index;
    string name;
    private static RecRunManager instance = null;
    private RecRunManager(){
	list = new ArrayList<RecRunNode>();
	index = 0;
	Date date = new Date();
	name = date.toString();
    }

    public static RecRunManager getManager(){
	if(instance == null){
	    instance = new RecRunManager;
	}
	return instance;
    }
    public void push(RecRunNode record){
	list.add(index, record);
	index++;
    }
    public RecRunNode getNxt(){
	if(index > list.size()){
	    RecRunNode ret = list.remove(index);
	    index++;
	    return ret;
	} else {
	    throw new RecRunDoneException();
	}
    }
    
    public setFileName(string name){
	this.name = name;
    }
    File getFile(){
	File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS, name);

	return file;
    }
    
    void writeFile(){
	FileOutputStream fos = new FileOutputStream(this.getFile());
	ObjectOutputStream oos = new ObjectOutputStream(fos);
	oos.writeObject(list);

	oos.close();
    }
    
    void readFile(){
	FileInputStream fis = new FileInputStream(this.getFile());
	ObjectInputStream ois = new ObjectInputStream(fis);
	list = (ArrayList<RecRunNode>) ois.readObject();

	ois.close();
    }
}

