package jpl.ch02.ex02;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

	@Test
	public void testLinkedListObjectLinkedList() {
		LinkedList nextList = new LinkedList(new Object());
		LinkedList test = new LinkedList(new Object(),nextList);
		assertNotNull(test);
		//fail("Not yet implemented");
	}

	@Test
	public void testLinkedListObject() {
		LinkedList test = new LinkedList(new Object());
		assertNotNull(test);
		//fail("Not yet implemented");
	}

}
