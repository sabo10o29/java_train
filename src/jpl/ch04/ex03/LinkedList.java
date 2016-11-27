package jpl.ch04.ex03;

public class LinkedList implements ILinkedList{
	private Object obje;
	private LinkedList next = null;
	
	//クローンメソッド
	public LinkedList clone(){
		LinkedList obj = null;
		try {
			obj = (LinkedList)super.clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		return obj;
	}
	
	//コンストラクタ
	LinkedList(Object _obje, LinkedList _next){
		this.obje = _obje;
		this.next = _next;
	}
	LinkedList(Object _obje){
		this.obje = _obje;
	}
	
	public void DispLink(){
		if(this.next != null){
			System.out.println("List is exist");
			this.next.DispLink();
		}else{
			System.out.println("Finish list");
		}
	}
	
	public String toString(){
		String str = this.obje.toString();
		if(this.next != null){
			str += "next" + this.next.toString();
		}
		return str;
	}
	
	//ゲッターセッター
	public Object getObject(){
		return this.obje;
	}
	public void setObject(Object _obje){
		this.obje = _obje;
	}
	public LinkedList getNextList(){
		return this.next;
	}
	public void setNextLinkedList(LinkedList _next){
		this.next = _next;
	}
	
	public int getNumList(){
		int Num = 1;
		if(this.next!=null){
			return this.next.getNumList()+1;
		}else{
			return Num;
		}
	}
	
}
