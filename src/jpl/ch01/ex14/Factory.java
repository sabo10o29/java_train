package jpl.ch01.ex14;

public class Factory {
	public static int nextSirial = 0;

	public static int getNextSirial(){
		nextSirial++;
		return nextSirial;
	}

}
