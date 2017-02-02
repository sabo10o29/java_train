package jpl.ch13.ex06;

import static org.junit.Assert.*;
import org.junit.Test;
import StdCap.StdoutCapture;
import jpl.ch13.ex05.SplitNum;

public class SplitNumTest {

	@Test
	public void testSplitnum() {
		assertTrue(SplitNum.splitnum("1543729", '@', 4).equals("154@3729"));
	}

}
