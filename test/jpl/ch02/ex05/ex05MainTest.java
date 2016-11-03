package jpl.ch02.ex05;

import static org.junit.Assert.*;

import org.junit.Test;

import StdCap.StdoutCapture;
import jpl.ch02.ex03.Vehicle;

public class ex05MainTest {

	@Test
	public void testMain() {
		String[] expected = new String[] {"Owner: taro","ID: 1","Angle: 2.0, Speed: 100"
										 ,"Owner: tanaka","ID: 2","Angle: 3.0, Speed: 200"
										 ,"Owner: yamada","ID: 3","Angle: 20.0, Speed: 340"};
		
		StdoutCapture sc = new StdoutCapture();
        sc.start();
        
		ex05.main(new String[0]);
		
		sc.stop();
        sc.assertEquals(expected);
		
		
		//fail("Not yet implemented");
	}

}
