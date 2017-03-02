package jpl.ch14.ex04;

public class HasValue {

	private static int value;
	
	public static synchronized void addSyncValue(){
		value++;
		Thread thread = Thread.currentThread();
		System.out.println(value + ": "+ thread.getName());
	}
	
	public static void main(String[] args){
		int i = 0;
		while(i < 100){
			new Thread(new CallMan()).start();
			i++;
		}

	}

}
