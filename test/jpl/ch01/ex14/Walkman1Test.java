package jpl.ch01.ex14;

import static org.junit.Assert.*;

import org.junit.Test;

import Hello.HelloWorld;
import StdCap.StdoutCapture;

public class Walkman1Test {

	Walkman1 test = new Walkman1(Factory.getNextSirial(),"Ver. 1");
	
	@Test
	public void testIniTerminal() {
		test.iniTerminal();
		assertEquals(test.getTerminal(),1);
		//fail("Not yet implemented");
	}

	@Test
	public void testWalkman1() {
		Walkman1 test2 = new Walkman1(Factory.getNextSirial(),"Ver. 1");
		assertNotNull(test2);
		//fail("Not yet implemented");
	}

	@Test
	public void testSetTerminal() {
		test.setTerminal(2);
		assertEquals(test.getTerminal(),2);
		//fail("Not yet implemented");
	}

	@Test
	public void testGetSirial() {
		//System.out.println(test.getSirial());
		assertEquals(test.getSirial(),7);
		//fail("Not yet implemented");
	}

	@Test
	public void testGetTerminal() {
		assertEquals(test.getTerminal(),1);
		//fail("Not yet implemented");
	}

	@Test
	public void testGetModel() {
		assertEquals(test.getModel(),"Ver. 1");
		//fail("Not yet implemented");
	}

	@Test
	public void testPrintSpec() {
		String[] expected = new String[] {"シリアル番号：2モデル：Ver. 1端子数1"};
        
        StdoutCapture sc = new StdoutCapture();
        sc.start();
        
        test.printSpec();
        
        sc.stop();
        sc.assertEquals(expected);
		//fail("Not yet implemented");
	}

}
