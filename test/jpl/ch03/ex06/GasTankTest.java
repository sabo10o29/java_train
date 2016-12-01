package jpl.ch03.ex06;

import static org.junit.Assert.*;
import org.junit.Test;

public class GasTankTest {

	@Test
	public void testEmpty() {
		GasTank tank = new GasTank();
		tank.fillGas(100);
		assertEquals(tank.gastank(),100);
	}

	@Test
	public void testGasTank() {
		GasTank tank = new GasTank();
		assertNotNull(tank);
	}

	@Test
	public void testFillGas() {
		GasTank tank = new GasTank();
		tank.fillGas(1000);
		assertEquals(tank.gastank(),1000);
		tank.fillGas(500);
		assertEquals(tank.gastank(),1500);
	}

	@Test
	public void testConsumeGas() {
		GasTank tank = new GasTank();
		tank.fillGas(1000);
		assertEquals(tank.gastank(),1000);
		tank.consumeGas(500);;
		assertEquals(tank.gastank(),500);
	}

}
