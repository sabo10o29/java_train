package jpl.ch01.ex16;
import java.io.IOException;

public class BadDataSetException extends Exception{
	public String name;
	public IOException exc;
	
	BadDataSetException(String _setName, IOException _e){
		this.name = _setName;
		this.exc = _e;
	}
}
