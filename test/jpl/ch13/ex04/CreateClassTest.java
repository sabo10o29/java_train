package jpl.ch13.ex04;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CreateClassTest {

	@Test
	public void testGetClassList() {
		String str = "  \nTest NNNkakikukeko\nString aaansasisuseso\nDouble 1222";
		ArrayList<Object> test = CreateClass.getClassList(str);
		assertTrue(test.get(0).toString().equals("aaansasisuseso"));
		assertTrue(test.get(1).toString().equals("1222.0"));
	}

}
