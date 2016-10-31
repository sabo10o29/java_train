package jpl.ch02.ex03;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testVehicle() {
		Vehicle test = new Vehicle(10,"Yoshikazu Murase",60);
		assertNotNull(test);
		//fail("Not yet implemented");
	}

	@Test
	public void testShowInfo() {
		Vehicle test = new Vehicle(10,"Yoshikazu Murase",60);
		test.showInfo();
		//fail("Not yet implemented");
	}

}
