package jpl.ch02.ex07;

import static org.junit.Assert.*;

import org.junit.Test;

import StdCap.StdoutCapture;
import jpl.ch02.ex05.ex05;

public class ex07MainTest {

	@Test
	public void testMain() {
		String[] expected = new String[] {
				"Owner: Yoshikazu","ID: 1","Angle: 0.0, Speed: 0"
			   ,"Owner: Yoshikazu","ID: 2","Angle: 0.0, Speed: 0"};

		StdoutCapture sc = new StdoutCapture();
		sc.start();

		ex07.main(new String[0]);

		sc.stop();
		sc.assertEquals(expected);
		
		//fail("Not yet implemented");
	}

}
