package jpl.ch07.ex01;

import static org.junit.Assert.*;

import org.junit.Test;
import StdCap.StdoutCapture;

public class TestUnicodeHelloWorld {

	@Test
	public void testMain() {
		String[] expected = new String[] {"Ｈｅｌｌｏ　Ｗｏｒｌｄ！"};
        
        StdoutCapture sc = new StdoutCapture();
        sc.start();
        
        UnicodeHelloWorld.main(new String[0]);
        
        sc.stop();
        sc.assertEquals(expected);
	}

}
