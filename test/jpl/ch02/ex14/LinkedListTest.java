package jpl.ch02.ex14;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {
	
	@Test
	public void testGetObject() {
		Object obj = new Object();
		LinkedList test = new LinkedList(obj,null);
		assertEquals(test.getObject(),obj);
	}

	@Test
	public void testGetNextList() {
		LinkedList test2 = new LinkedList(new Object(),null);
		LinkedList test1 = new LinkedList(new Object(),test2);
		assertEquals(test1.getNextList(),test2);
	}

	@Test
	public void testSetNextList() {
		LinkedList test1 = new LinkedList(new Object(),null);
		LinkedList test2 = new LinkedList(new Object(),null);
		test1.setNextList(test2);
		assertEquals(test1.getNextList(),test2);
	}

}
