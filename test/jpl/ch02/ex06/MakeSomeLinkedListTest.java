package jpl.ch02.ex06;

import static org.junit.Assert.*;

import org.junit.Test;

import StdCap.StdoutCapture;
import jpl.ch02.ex07.OverLoadConstVehicle;

public class MakeSomeLinkedListTest {

	@Test
	public void testMain() {
		String[] expected = new String[] {	"List is exist",
											"List is exist",
											"List is exist",
											"List is exist",
											"List is exist",
											"List is exist",
											"Finish list"};

		StdoutCapture sc = new StdoutCapture();
		sc.start();

		MakeSomeLinkedList.main(new String[0]);

		sc.stop();
		sc.assertEquals(expected);
		//fail("Not yet implemented");
	}

}
