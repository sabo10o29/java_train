package jpl.ch14.ex05;

public class HasValue {

	private int value = Integer.MAX_VALUE;
	private final Object lock = new Object();
	
	public void subtractSyncValue(){
		synchronized (lock) {
			value--;
			Thread thread = Thread.currentThread();
			System.out.println(value + ": "+ thread.getName());
		}
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
