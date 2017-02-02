package jpl.ch13.ex02;

import static org.junit.Assert.*;
import org.junit.Test;

public class FindCharSequenceTest {

	@Test
	public void testFind() {
		assertEquals(2, FindCharSequence.find("aieoieieovj","eo"));
	}

}
