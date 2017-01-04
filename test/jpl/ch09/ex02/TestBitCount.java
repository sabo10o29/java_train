package jpl.ch09.ex02;

import static org.junit.Assert.*;

import org.junit.Test;

import StdCap.StdoutCapture;
import jpl.ch07.ex03.Pascal;

public class TestBitCount {

	@Test
	public void testBitCount() {
		
		//test
		String[] expected = new String[] {"101","Number of '1' is 2"};
        StdoutCapture sc = new StdoutCapture();
        sc.start();
        BitCount test = new BitCount(5);
        sc.stop();
        sc.assertEquals(expected);
		
		
	}

}
