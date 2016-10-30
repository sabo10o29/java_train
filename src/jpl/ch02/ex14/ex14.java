package jpl.ch02.ex14;

/***
 * LinkedListにアクセッサ-メソッドを追加する
 * リスト構造はクラスの参照先を変化させることでリストを作成する
 * このため、保持するオブジェクトを変化することはないが、連結する次のLinkedListは変化する。
 * 以上のことから、保持するObjectにはセッターは必要ないが、次のLinkedListへの参照はゲッターとセッターが必要になる
 * @author murase
 *
 */

public class ex14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//リスト１の作成
		LinkedList list1 = new LinkedList(new Vehicle(30,"Yoshikazu Murase",100), null);
		
		System.out.println("Object: " + list1.getObject().toString());
		System.out.println("Owner: " + list1.getNextList());
		
		//リスト２の作成
		LinkedList list2 = new LinkedList(new Vehicle(40,"Tanaka Taro",300), null);
		//リスト１にリスト２を追加
		list1.setNextList(list2);
		
		System.out.println("Object: " + list1.getObject().toString());
		System.out.println("Owner: " + list1.getNextList());

	}

}
