package jpl.ch02.ex10;

import static org.junit.Assert.*;

import org.junit.Test;

import StdCap.StdoutCapture;
import jpl.ch02.ex06.MakeSomeLinkedList;

public class VehicleTest {

	@Test
	public void testToString() {
		
		Vehicle test = new Vehicle(10,"Yoshikazu",20);
		
		StdoutCapture sc = new StdoutCapture();
		sc.start();

		System.out.println(test.toString());

		sc.stop();
		sc.assertEquals("1(Yoshikazu)");
		
		
		//fail("Not yet implemented");
	}

}
