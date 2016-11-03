package jpl.ch02.ex14;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {
	
	@Test
	public void testGetObject() {
		LinkedList test = new LinkedList(new Object(),null);
		assertNotNull(test.getObject());
		
		//fail("Not yet implemented");
	}

	@Test
	public void testGetNextList() {
		LinkedList test = new LinkedList(new Object(),null);
		assertNull(test.getNextList());
		//fail("Not yet implemented");
	}

	@Test
	public void testSetNextList() {
		LinkedList test = new LinkedList(new Object(),null);
		test.setNextList(new LinkedList(new Object()));
		assertNotNull(test.getNextList());
		//fail("Not yet implemented");
	}

}
