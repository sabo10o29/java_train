package jpl.ch12.ex01;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;
/***
 * nullを返す場合、その後の処理の有無は開発者に委ねられるため、意識的に例外処理を実装しなければならない。
 * 一方で、例外をスローした場合には上の階層でtrycatchか、throws節を必ず書かなければエラーが発生するため、意識しなくとも例外処理を実装できる。
 * 
 * @author YoshikazuMurase
 *
 */
public class TestException {

	@Test
	public void testFind() {
		LinkedList<Color> list;
		LinkedList<Color> next;
		LinkedList<Color> last = new LinkedList<Color>(Color.BLUE); 
		
		list = new LinkedList<Color>(Color.RED, last);
		next = new LinkedList<Color>(Color.cyan, list);
		list = new LinkedList<Color>(Color.GRAY, next);
		next = new LinkedList<Color>(Color.PINK, list);
		list = new LinkedList<Color>(Color.GREEN, next);
		
		
		String target = Color.RED.toString();
		try {
			LinkedList<Color> result = list.find(target);
			System.out.println("Find object");
			assertTrue(target.equals(result.getElement().toString()));
		} catch (ObjectNotFoundException e) {
			// TODO 自動生成された catch ブロック
			System.out.println(e.name + "is not found.");
			assertTrue(target.equals(e.name.toString()));
		}

	}
	
	@Test
	public void testFindException() {
		LinkedList<Color> list;
		LinkedList<Color> next;
		LinkedList<Color> last = new LinkedList<Color>(Color.BLUE); 
		
		list = new LinkedList<Color>(Color.RED, last);
		next = new LinkedList<Color>(Color.cyan, list);
		list = new LinkedList<Color>(Color.GRAY, next);
		next = new LinkedList<Color>(Color.PINK, list);
		list = new LinkedList<Color>(Color.GREEN, next);
		
		
		String target = Color.BLACK.toString();
		try {
			LinkedList<Color> result = list.find(target);
			System.out.println("Find object");
			assertTrue(target.equals(result.getElement().toString()));
		} catch (ObjectNotFoundException e) {
			System.out.println(e.name + "is not found.");
			assertTrue(target.equals(e.name.toString()));
		}

	}

}
