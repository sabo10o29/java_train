package jpl.ch02.ex09;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {
	
	@Test
	public void testMaxID() {
		int MAXSIZE = 100;
		for(int i=0; i<MAXSIZE; i++){
			new Vehicle(10,"Yoshikazu",20);
		}
		assertEquals(Vehicle.maxID(),100);
		//fail("Not yet implemented");
	}

}
