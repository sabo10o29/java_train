package jpl.ch14.ex06;

import java.util.TimerTask;

public class CallTimer extends TimerTask{
	
	private PrintTime timer;
	
	public CallTimer(PrintTime _timer) {
		timer = _timer;
	}
	
	@Override
	public void run() {
		timer.timer();
	}

}
