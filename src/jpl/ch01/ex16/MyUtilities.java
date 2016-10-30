package jpl.ch01.ex16;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;


public class MyUtilities {
	public static final int MAX_SIZE = 100;
	
	public double[] getDataSet(String setName) throws BadDataSetException{
	
		String file = setName + ".dset";
		FileInputStream in = null;
		try{
			in = new FileInputStream(file);
			return readDataSet(in);
			
		}catch(IOException e){
			throw new BadDataSetException(setName, e);
		}finally{
			try{
				if(in != null)in.close();
			}catch(IOException e){
				;
			}
		}
	}
	
	public double[] readDataSet(FileInputStream _in) throws IOException{
		double[] data = new double[MAX_SIZE];
		int count = 0;
		int num;
		
		while((num = _in.read())!=-1){
			data[count] = num;
			count++;
		}
		return data;
	}
	
}
