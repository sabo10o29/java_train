package jpl.ch11.ex01;

import static org.junit.Assert.*;

import org.junit.Test;

import com.sun.prism.paint.Color;

public class LinkedListTest {

	@Test
	//リストにほかの型が挿入できないかテスト
	public void testLinkedListELinkedListOfE() {
		LinkedList<Color> test = new LinkedList<Color>(Color.BLACK);
		test.setNextLinkedList(new LinkedList<Color>(Color.BLUE));
		//test.setNextLinkedList(new LinkedList<Object>(new Object()));
	}
	
	//複数のオブジェクトを挿入して正確な個数を返却するかテスト
	@Test
	public void testGetNumList() {
		LinkedList<Color> test = new LinkedList<Color>(Color.BLACK);
		test.setNextLinkedList(new LinkedList<Color>(Color.BLUE));
		assertEquals(2, test.getNumList());
	}

}
