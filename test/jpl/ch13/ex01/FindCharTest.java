package jpl.ch13.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

public class FindCharTest {

	@Test
	public void testFind() {
		assertEquals(4, FindChar.find("aieeieieovj",'e'));
	}

}
