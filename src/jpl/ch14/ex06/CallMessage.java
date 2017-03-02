package jpl.ch14.ex06;

public class CallMessage implements Runnable{

	private final PrintTime timer;
	private final int interval;
	
	public CallMessage(PrintTime _timer, int _interval) {
		timer = _timer;
		interval = _interval;
	}
	
	@Override
	public void run() {
		while(true){
			execute();
		}
	}
	
	private final void execute(){
		try {
			timer.message(interval);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
