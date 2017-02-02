package jpl.ch11.ex02;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.prism.paint.Color;

public class AttrTest {

	@Test
	public void testAttr() {
		Attr<Color> test = new Attr<Color>("Black",Color.BLACK);
		//test.setValue(new Object());	ほかの型は代入できないことを確認
		test.setValue(Color.RED);
		assertTrue(Color.RED.toString().equals(test.getValue().toString()));		
	}

}
