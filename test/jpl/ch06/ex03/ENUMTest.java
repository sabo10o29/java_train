package jpl.ch06.ex03;

import static org.junit.Assert.*;

import org.junit.Test;

public class ENUMTest {

	@Test
	public void test() {
		assertEquals(Verbose.ENUM.SILENT.toString(),"0");
		assertEquals(Verbose.ENUM.TERSE.toString(),"1");
		assertEquals(Verbose.ENUM.NORMAL.toString(),"2");
		assertEquals(Verbose.ENUM.VERBOSE.toString(),"3");
		
	}

}
