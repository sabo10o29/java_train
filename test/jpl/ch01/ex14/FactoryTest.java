package jpl.ch01.ex14;

import static org.junit.Assert.*;

import org.junit.Test;

public class FactoryTest {

	@Test
	public void testGetNextSirial() {
		Factory.getNextSirial();
		Integer test = Factory.getNextSirial();
		test.equals(2);
		//fail("Not yet implemented");
	}

}
