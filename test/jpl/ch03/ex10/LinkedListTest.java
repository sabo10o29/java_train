package jpl.ch03.ex10;

import static org.junit.Assert.*;

import org.junit.Test;

import Hello.HelloWorld;
import StdCap.StdoutCapture;

public class LinkedListTest {

	@Test
	public void testMain() {
		String[] expected = new String[] {
				"340",
				"340",
				"",
				"160",
				"160",
				"",
				"160",
				"600"
		};
        
        StdoutCapture sc = new StdoutCapture();
        sc.start();
        
        LinkedList.main(new String[0]);
        
        sc.stop();
        sc.assertEquals(expected);
	}

}
