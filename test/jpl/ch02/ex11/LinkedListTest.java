package jpl.ch02.ex11;

import static org.junit.Assert.*;

import org.junit.Test;

import StdCap.StdoutCapture;

public class LinkedListTest {

	@Test
	public void testToString() {
		Vehicle[] cars = {
				new Vehicle(20,"matsuda",100),
				new Vehicle(30,"toyota",200),
				new Vehicle()
		};
		
		//
		LinkedList last = new LinkedList(cars[0]);
		LinkedList before;
	
		LinkedList list = new LinkedList(cars[1], last);
		for(int i=2; i<cars.length; i++){
			before = list;
			list = new LinkedList(cars[i], before);
		}
		
		StdoutCapture sc = new StdoutCapture();
		sc.start();
		
		System.out.println(list.toString());
		
		sc.stop();
		sc.assertEquals("3(toyota)next2(toyota)next1(matsuda)");
		
		
		//fail("Not yet implemented");
	}

}
