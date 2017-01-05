package jpl.ch10.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

import Hello.HelloWorld;
import StdCap.StdoutCapture;

public class TestChangeStr {

	@Test
	public void testChangeStr() {
		
		 String[] expected = new String[] {"\\tes\\t \\n \\765"};
	        
	        StdoutCapture sc = new StdoutCapture();
	        sc.start();
	        
	        ChangeStr test = new ChangeStr("test n 765");
	        
	        sc.stop();
	        sc.assertEquals(expected);
		
		
	}

}
