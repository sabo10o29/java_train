package jpl.ch14.ex09;

public class Hoge implements Runnable{

	@Override
	public void run() {
		try {
			Thread.sleep(30000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
