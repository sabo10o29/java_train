package jpl.ch02.ex15;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testChangeSpeed() {
		Vehicle test = new Vehicle(10,"Yoshikazu", 200);
		test.changeSpeed(340);
		assertEquals(test.getSpeed(),340);
	}

	@Test
	public void testStop() {
		Vehicle test = new Vehicle(10,"Yoshikazu", 200);
		test.stop();
		assertEquals(test.getSpeed(),0);
		
	}

}
