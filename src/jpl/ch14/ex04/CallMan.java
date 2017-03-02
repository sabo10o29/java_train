package jpl.ch14.ex04;


public class CallMan implements Runnable{
	
	CallMan(){

	}
	
	@Override
	public void run() {
		while(true){
			try {
				HasValue.addSyncValue();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
}
