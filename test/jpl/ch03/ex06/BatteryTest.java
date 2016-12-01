package jpl.ch03.ex06;

import static org.junit.Assert.*;

import org.junit.Test;

public class BatteryTest {

	@Test
	public void testEmpty() {
		Battery battery = new Battery();
		battery.chargeBattery(100);
		assertEquals(battery.battery(),100);
	}

	@Test
	public void testBattery() {
		Battery battery = new Battery();
		assertNotNull(battery);
	}

	@Test
	public void testChargeBattery() {
		Battery battery = new Battery();
		battery.chargeBattery(1000);
		assertEquals(battery.battery(),1000);
		battery.chargeBattery(500);
		assertEquals(battery.battery(),1500);
	}

	@Test
	public void testConsumeBattery() {
		Battery battery = new Battery();
		battery.chargeBattery(1000);
		battery.consumeBattery(500);
		assertEquals(battery.battery(),500);
	}

}
