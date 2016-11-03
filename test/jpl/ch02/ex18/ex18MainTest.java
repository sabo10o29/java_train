package jpl.ch02.ex18;

import static org.junit.Assert.*;

import org.junit.Test;

import StdCap.StdoutCapture;

public class ex18MainTest {

	@Test
	public void testMain() {
		String[] expected = new String[] {"Owner: Yoshikazu","ID: 1","Angle: 5.0, Speed: 250"};
		String[] name = new String[] {"Yoshikazu"};
		
		StdoutCapture sc = new StdoutCapture();
        sc.start();
        
		ex18.main(name);
		
		sc.stop();
        sc.assertEquals(expected);
		
	}

}
