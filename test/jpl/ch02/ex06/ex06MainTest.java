package jpl.ch02.ex06;

import static org.junit.Assert.*;

import org.junit.Test;

import StdCap.StdoutCapture;
import jpl.ch02.ex07.ex07;

public class ex06MainTest {

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

		ex06.main(new String[0]);

		sc.stop();
		sc.assertEquals(expected);
		//fail("Not yet implemented");
	}

}
