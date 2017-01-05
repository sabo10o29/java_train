package jpl.ch10.ex02;

import static org.junit.Assert.*;

import org.junit.Test;

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
