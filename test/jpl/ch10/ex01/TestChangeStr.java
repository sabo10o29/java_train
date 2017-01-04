package jpl.ch10.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestChangeStr {

	@Test
	public void testReplaceStr() {
		
		String[] a = {"Hello World!"};
		String[] b = ChangeStr.replaceStr(a);
		
		String[] c = {"Hello World?"};
		
		assertTrue(c[0].equals(b[0]));
		
		
	}

}
