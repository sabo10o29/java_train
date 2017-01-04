package jpl.ch07.ex03;

import static org.junit.Assert.*;

import org.junit.Test;

import Hello.HelloWorld;
import StdCap.StdoutCapture;

public class TestPascal {

	@Test
	public void testPascal() {
		
		//test1
		String[] expected = new String[] {""};
        StdoutCapture sc = new StdoutCapture();
        sc.start();
        Pascal test = new Pascal(0);
        test.showPascal();
        sc.stop();
        sc.assertEquals(expected);
        
      //test2
      expected = new String[] {"1 "};
      sc = new StdoutCapture();
      sc.start();
      test = new Pascal(1);
      test.showPascal();
      sc.stop();
      sc.assertEquals(expected);
      
      //test3
      expected = new String[] {"1 ","1 1 "};
      sc = new StdoutCapture();
      sc.start();
      test = new Pascal(2);
      test.showPascal();
      sc.stop();
      sc.assertEquals(expected);  
		
      //test4
      expected = new String[] {"1 ","1 1 ","1 2 1 "};
      sc = new StdoutCapture();
      sc.start();
      test = new Pascal(3);
      test.showPascal();
      sc.stop();
      sc.assertEquals(expected);
      
	}

}
