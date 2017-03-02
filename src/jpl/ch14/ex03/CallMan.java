package jpl.ch14.ex03;

public class CallMan implements Runnable{
	
	HasValue test;
	
	CallMan(HasValue _test){
		test = _test;
	}
	
	@Override
	public void run() {
		while(true){
			try {
				test.addSyncValue();
				//test.addAsyncValue();	
				Thread.sleep(10);
			} catch (InterruptedException e) {
				return;
			}
		}
	}
	
	
	
}
