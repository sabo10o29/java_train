package jpl.ch04.ex03;

import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch04.ex03.LinkedList;
import jpl.ch04.ex03.Vehicle;

public class LinkedListTest {
	
	@Test
	public void testGetObject() {
		Object obj = new Object();
		LinkedList test = new LinkedList(obj,null);
		assertEquals(test.getObject(),obj);
	}

	@Test
	public void testSetObject() {
		LinkedList test1 = new LinkedList(new Object(),null);
		LinkedList test2 = new LinkedList(new Object(),null);
		test1.setObject(test2);
		assertEquals(test1.getObject(),test2);
	}

	@Test
	public void testGetNextList() {
		LinkedList test2 = new LinkedList(new Object(),null);
		LinkedList test1 = new LinkedList(new Object(),test2);
		assertEquals(test1.getNextList(),test2);
	}

	@Test
	public void testSetNextLinkedList() {
		LinkedList test1 = new LinkedList(new Object(),null);
		LinkedList test2 = new LinkedList(new Object(),null);
		test1.setNextLinkedList(test2);
		assertEquals(test1.getNextList(),test2);
	}

	@Test
	public void testGetNumList() {
		Vehicle[] cars = {
				new Vehicle(2,"matsuda",100),
				new Vehicle(3,"toyota",200),
				new Vehicle(20,"honda",300),
				new Vehicle(20,"kawasaki",400),
				new Vehicle(20,"suzuki",250),
				new Vehicle(20,"nissan",345),
				new Vehicle(20,"yamaha",65)
		};
		
		LinkedList last = new LinkedList(cars[0]);
		LinkedList before;
	
		LinkedList list = new LinkedList(cars[1], last);
		for(int i=2; i<cars.length; i++){
			before = list;
			list = new LinkedList(cars[i], before);
		}
		System.out.println("Number of cars: "+list.getNumList());
		
		assertEquals(list.getNumList(),7);
	}

}
