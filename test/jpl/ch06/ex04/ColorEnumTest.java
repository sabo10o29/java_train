package jpl.ch06.ex04;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class ColorEnumTest {

	@Test
	public void test() {
		assertEquals(ColorEnum.Colorenum.RED.getColor().toString(),Color.red.toString());
		assertEquals(ColorEnum.Colorenum.BLUE.getColor().toString(),Color.blue.toString());
		assertEquals(ColorEnum.Colorenum.YELLOW.getColor().toString(),Color.yellow.toString());
	}

}
