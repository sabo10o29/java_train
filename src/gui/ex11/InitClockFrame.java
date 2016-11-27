package gui.ex11;
import java.awt.*;

class InitClockFrame extends Frame{
	static final int frameWidth = 400;
	static final int frameHeight = 300;
	
	InitClockFrame(){
		this.setSize(frameWidth, frameHeight);
		this.addWindowListener(new ClockWindowAdapter());
	}
	
	
}
