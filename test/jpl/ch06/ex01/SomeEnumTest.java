package jpl.ch06.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

public class SomeEnumTest {

	@Test
	public void test() {
		assertEquals(SomeEnum.TraficLight.BLUE.toString(),"BLUE");
		assertEquals(SomeEnum.Week.SUNDAY.toString(),"SUNDAY");
	}

}
