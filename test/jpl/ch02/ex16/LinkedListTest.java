package jpl.ch02.ex16;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTest {

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
