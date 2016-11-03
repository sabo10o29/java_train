package jpl.ch01.ex15;

import static org.junit.Assert.*;

import org.junit.Test;

import StdCap.StdoutCapture;
public class SimpleLookuoTest {
	
	@Test
	public void testAdd() {
		SimpleLookup lookup = new SimpleLookup(5);
		String[] expected = new String[] {"0","1","2","3"};
        
        StdoutCapture sc = new StdoutCapture();
        sc.start();
		
		
		for(Integer i = 0 ;i<4;i++){
			lookup.add(i.toString());
		}
		lookup.print();
		
		sc.stop();
        sc.assertEquals(expected);
        //lookup.print();
	}
		//fail("Not yet implemented");

	@Test
	public void testRemove() {
		SimpleLookup lookup = new SimpleLookup(5);
		String[] expected = new String[] {"0","2","3"};
        
        StdoutCapture sc = new StdoutCapture();
        sc.start();
		
		
		for(Integer i = 0 ;i<4;i++){
			lookup.add(i.toString());
		}
		lookup.remove("1");
		lookup.print();
		
		sc.stop();
        sc.assertEquals(expected);
        //lookup.print();
		
		
	}

}
