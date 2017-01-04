package jpl.ch10.ex05;

import static org.junit.Assert.*;

import org.junit.Test;

import Hello.HelloWorld;
import StdCap.StdoutCapture;

public class TestListChar {

	@Test
	public void testListchar() {
		String[] expected = new String[] {"abcd"};
        
        StdoutCapture sc = new StdoutCapture();
        sc.start();
        
        ListChar.listchar('a', 'd');
        
        sc.stop();
        sc.assertEquals(expected);
	}

}
