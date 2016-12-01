package jpl.ch04.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

public class BatteryTest {

	@Test
	public void testEmpty() {
		Battery test = new Battery();
		assertFalse(test.empty());
		test.chargeBattery(100);
		assertTrue(test.empty());
	}

}
