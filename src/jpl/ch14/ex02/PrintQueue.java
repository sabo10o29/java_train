package jpl.ch14.ex02;
import java.util.NoSuchElementException;

import sun.misc.Queue;

public class PrintQueue extends Queue<PrintJob>{
	
	public void add(PrintJob job) {
		this.add(job);
	}

	@SuppressWarnings("finally")
	public PrintJob remove() {
		
		PrintJob result = null;
		try{
			result = this.remove();
		}catch (NoSuchElementException e){
		}finally{
			return result;
		}
		
	}
	

}
