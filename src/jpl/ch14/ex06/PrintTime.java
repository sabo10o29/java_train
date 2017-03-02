package jpl.ch14.ex06;

import java.util.Timer;

public class PrintTime{
	
	private int time = 0;

	public static void main(String[] args) {
		PrintTime test = new PrintTime();		
		Timer timer = new Timer();
		timer.schedule(new CallTimer(test), 0, 1000);
		
		CallMessage messengerA = new CallMessage(test, 7);
		CallMessage messengerB = new CallMessage(test, 15);
		new Thread(messengerA).start();
		new Thread(messengerB).start();
	}
	
	public synchronized void timer() {
		time++;
		System.out.println(time + "sec");
		notifyAll();
	}
	
	public synchronized void message(int interval) throws InterruptedException{
		while( time % interval != 0){
			wait();
		}
		System.out.println(time + "sec 経過しました。(" + interval + "sec 経過通知）");
		wait();
	}

}
