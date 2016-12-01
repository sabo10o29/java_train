package jpl.ch03.ex07;

import static org.junit.Assert.*;

import org.junit.Test;

public class AttrTest {

	@Test
	public void testAttr() {
		Attr test = new Attr("test",new Object());
		assertNotNull(test);
	}

	@Test
	public void testGetName() {
		Attr test = new Attr("test",new Object());
		assertEquals(test.getName(),"test");
	}

	@Test
	public void testGetValue() {
		Object obj = new Object();
		
		Attr test = new Attr("test",obj);
		assertEquals(test.getValue(),obj);
	}

	@Test
	public void testSetValue() {
		Attr test = new Attr("test",new Object());
		Object obj = new Object();
		test.setValue(obj);
		assertEquals(test.getValue(),obj);
		
	}

}
