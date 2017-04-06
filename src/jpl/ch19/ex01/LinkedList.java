package jpl.ch19.ex01;

/**
 * LinkedListクラスはオブジェクトを保持する片方向リストを作成するクラスです。
 * 一つのLinkedListクラスのインスタンスは、一つのオブジェクトと次のLinkedListへの参照を持つことができます。
 * 
 * 
 * @version 1.0
 * @author YoshikazuMurase
 *
 */
public class LinkedList {
	/**
	 * 値として保持するためのオブジェクト
	 */
	private Object obje;
	/**
	 * 次のLinkedList
	 */
	private LinkedList next = null;
	
	/**
	 * objeをフィールドに持ち、nextへの参照を持つインスタンスを生成します。
	 * @param obje 保持するオブジェクト
	 * @param next 次のLinkedList
	 */
	LinkedList(Object obje, LinkedList next){
		this.obje = obje;
		this.next = next;
	}
	/**
	 * デフォルトコンストラクタ
	 * objeをフィールドに持ち、次のLinkedListへの参照を持たないインスタンスを生成します。
	 * @param obje 保持するObject
	 */
	LinkedList(Object obje){
		this.obje = obje;
	}
	/**
	 * すべての連結しているリストが保持しているObjectのtoStringを返します。
	 * 片方向循環リストの場合には使用できません！
	 */
	public String toString(){
		String str = this.obje.toString();
		if(this.next != null){
			str += "next" + this.next.toString();
		}
		return str;
	}
	
	/**
	 * 保持しているObjectを返します。
	 * Objectを保持していない場合にはnullを返します。
	 * @return 保持しているObject
	 */
	public Object getObject(){
		return this.obje;
	}
	/**
	 * Objectをフィールドにセットします。
	 * @param obje 保持するObject
	 */
	public void setObject(Object obje){
		this.obje = obje;
	}
	/**
	 * 次のLinkedListへの参照を返します。
	 * 次のLinkedListが存在しない場合にはnullを返します。
	 * @return 次のLinkedList
	 */
	public LinkedList getNextList(){
		return this.next;
	}
	/**
	 * 連結しているリストの個数を返します。
	 * 片方向循環リストの場合には使用できません！
	 * @return 連結しているリストの数
	 */
	public int getNumList(){
		int Num = 1;
		if(this.next!=null){
			return this.next.getNumList()+1;
		}else{
			return Num;
		}
	}
	
}
