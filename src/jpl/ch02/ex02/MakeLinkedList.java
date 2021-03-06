package jpl.ch02.ex02;

/***
 * Object型のフィールドと次の要素への参照をもつクラスを作成する。
 * @author murase
 *
 */

public class MakeLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList last = new LinkedList(new Object());
		LinkedList before;
	
		LinkedList list = new LinkedList(new Object(), last);
		for(int i=0; i<100; i++){
			before = list;
			list = new LinkedList(new Object(), before);
		}
		list.DispLink();
	}

}
