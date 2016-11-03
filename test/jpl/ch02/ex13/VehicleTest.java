package jpl.ch02.ex13;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {
	
	@Test
	public void testGetID() {
		Vehicle test = new Vehicle(100,"Yoshikazu",200);
		//System.out.println(this.test.getID());
		Integer exp = (int)4;
		assertTrue(exp.equals(test.getID()));
	}
	
	@Test
	public void testGetAngle() {
		Vehicle test = new Vehicle(100,"Yoshikazu",200);
		Double exp = (double) 100;
		assertTrue(exp.equals(test.getAngle()));
		//System.out.println(this.test.getID());
	}

	@Test
	public void testSetAngle() {
		Vehicle test = new Vehicle(100,"Yoshikazu",200);
		test.setAngle(200);
		Double exp = (double) 200;
		assertTrue(exp.equals(test.getAngle()));
		//System.out.println(this.test.getID());
	}

	@Test
	public void testGetOwner() {
		Vehicle test = new Vehicle(100,"Yoshikazu",200);
		assertEquals(test.getOwner(),"Yoshikazu");
	}

	@Test
	public void testGetSpeed() {
		Vehicle test = new Vehicle(100,"Yoshikazu",200);
		Double exp = (double) 200;
		assertTrue(exp.equals(test.getSpeed()));
	}

	@Test
	public void testSetSpeed() {
		Vehicle test = new Vehicle(100,"Yoshikazu",200);
		test.setSpeed(240);
		Double exp = (double) 240;
		assertTrue(exp.equals(test.getSpeed()));
	}

}
