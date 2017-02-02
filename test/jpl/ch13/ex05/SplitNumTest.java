package jpl.ch13.ex05;

import static org.junit.Assert.*;
import org.junit.Test;
import StdCap.StdoutCapture;

public class SplitNumTest {

	@Test
	public void testSplitnum() {
		assertTrue(SplitNum.splitnum("1543729", ',', 3).equals("1,543,729"));
	}

}
