package jpl.ch02.ex17;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testTurnDouble() {
		Vehicle car = new Vehicle(5,"Yoshikazu Murase",250);
		car.turn(20);
		Double exp = (double)25;
		car.showInfo();
		assertTrue(exp.equals(car.getAngle()));
	}

	@Test
	public void testTurnInt() {
		Vehicle car = new Vehicle(5,"Yoshikazu Murase",250);
		car.turn(Vehicle.TURN_RIGHT);
		Double exp = (double)95;
		car.showInfo();
		assertTrue(exp.equals(car.getAngle()));
	}

}
