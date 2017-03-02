package jpl.ch14.ex05;


public class CallMan implements Runnable{
	
	private HasValue value;
	
	CallMan(HasValue value){
		this.value = value;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				value.subtractSyncValue();
				Thread.sleep(10);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
}
