package jpl.ch21.ex07;

import java.util.ArrayList;
import java.util.EmptyStackException;

/**
 * ArrayListを内部で使用すべき
 * 
 * @author YoshikazuMurase
 * @param <E>
 *
 */
public class Stack<E> {
	
	private ArrayList<E> list = new ArrayList<E>();
	
	public Stack(){
	}
	
	public boolean empty(){
		return list.isEmpty();
	}
	
	public E peek(){
		E val = null;
		try{
			val = list.get(0);
		}catch(IndexOutOfBoundsException e){
			throw new EmptyStackException();
		}
		
		return val;
	}
	
	public E pop(){
		E val = null;
		try{
			val = list.remove(0);
		}catch(IndexOutOfBoundsException e){
			throw new EmptyStackException();
		}
		
		return val;
	}

	public E push(E item) {
		list.add(0, item);	//先頭にアイテムを追加
		return item;
	}
	
	public int search(Object o){
		return list.indexOf(o);
	}
	
	public static void main(String[] args) {
		Stack<String> test = new Stack<String>();
		try {
			test.push("a");
			test.push("b");
			test.push("c");
			test.push("d");
			test.push("e");
			test.push("f");
			test.push("g");
			System.out.println("値の取り出し："+test.pop());
			System.out.println("値の取り出し："+test.pop());
			System.out.println("値の取り出し（peek）："+test.peek());
			System.out.println("値の取り出し："+test.pop());
			System.out.println("値の取り出し："+test.pop());
			System.out.println("bが存在するかチェック：" + test.search("b"));
			System.out.println("fが存在するかチェック：" + test.search("f"));
			System.out.println("現在空かどうかチェック：" + test.empty());
			System.out.println("値の取り出し："+test.pop());
			System.out.println("値の取り出し："+test.pop());
			System.out.println("値の取り出し："+test.pop());
			System.out.println("値の取り出し："+test.pop());
		} catch (EmptyStackException e) {
			System.out.println("値が存在しません");
		} finally {
			System.out.println("現在空かどうかチェック：" + test.empty());
		}
		
		
	}

}
