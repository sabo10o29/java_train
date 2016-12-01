package jpl.ch04.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

public class GasTankTest {

	@Test
	public void testEmpty() {
		GasTank test = new GasTank();
		assertFalse(test.empty());
		test.fillGas(100);
		assertTrue(test.empty());
	}

}
