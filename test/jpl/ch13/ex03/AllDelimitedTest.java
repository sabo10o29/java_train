package jpl.ch13.ex03;

import static org.junit.Assert.*;

import org.junit.Test;

public class AllDelimitedTest {

	@Test
	public void testDelimitedString() {
		String[] str = AllDelimited.delimitedString("<test>aiu<hoge>eoeo",'<','>');
		assertTrue(str[0].equals("<test>"));
		assertTrue(str[1].equals("<hoge>"));
	}

}
