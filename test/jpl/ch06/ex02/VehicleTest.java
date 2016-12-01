package jpl.ch06.ex02;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void test() {
		assertEquals(Vehicle.Turn.TURN_LEFT.value,-90);
		assertEquals(Vehicle.Turn.TURN_RIGHT.value,90);
	}

}
