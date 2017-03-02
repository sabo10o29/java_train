package jpl.ch14.ex03;

public class HasValue {
	
	private int value;
	
	public synchronized void addSyncValue(){
		value++;
		Thread thread = Thread.currentThread();
		System.out.println(value + ": "+ thread.getName());
	}
	public void addAsyncValue(){
		value++;
		Thread thread = Thread.currentThread();
		System.out.println(value + ": "+ thread.getName());
	}
	
	
	public static void main(String[] args){
		HasValue test = new HasValue();
		int i = 0;
		while(i < 100){
			new Thread(new CallMan(test)).start();
			i++;
		}

	}
	
}
