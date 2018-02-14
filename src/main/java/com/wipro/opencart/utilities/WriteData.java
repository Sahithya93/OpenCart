package com.wipro.opencart.utilities;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteData {
	
	BufferedWriter writer;
	
	public WriteData(String enterFileName) throws Exception{

	FileWriter wt=new FileWriter(System.getProperty("user.dir")+"\\TextFiles\\"+enterFileName+".txt",true);
	writer = new BufferedWriter(wt);
	}
	
	public void writeTextToFile(String text) throws IOException{
		
		writer.write(text);
		writer.newLine();
		writer.close();
		
	}
	
}
