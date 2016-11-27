package jpl.ch04.ex03;

/***
 * 1.ゲッターやセッター、リストされている数を数えるメソッドなどはリスト構造において、共通のメソッドであると考えられる。
 * このため、インターフェースとしてまとめることで他のリストクラスにも応用が可能になる。
 * 2.従来のLinkedListはCloneableを実装していたが、Cloneableを新しいインターフェースに含めることでリンク・リストに必要なメソッドを一つのパッケージとしてまとめることができる。
 * @author murase
 *
 */

interface ILinkedList extends Cloneable{
	
	void DispLink();							//
	
	Object getObject();							//リストのオブジェクトに関するゲッターセッター
	void setObject(Object _obje);				//
	
	LinkedList getNextList();					//次のリストに関するゲッターセッター
	void setNextLinkedList(LinkedList _next);	//
	
	int getNumList();							//リンクされているリストの数を返すメソッド
}
