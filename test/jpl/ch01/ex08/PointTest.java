package jpl.ch01.ex08;

import static org.junit.Assert.*;

import org.junit.Test;

public class PointTest {

	@Test
	public void testSetPoint() {
		Double x=(double)3;
		Double y=(double)4;
		
		Point test = new Point();
		test.setPoint(x.intValue(),y.intValue());
		
		assertTrue(x.equals(test.x));
		assertTrue(y.equals(test.y));
	}

}
