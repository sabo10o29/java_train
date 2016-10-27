package jpl.ch02.ex16;

public class LinkedList {
	private Object obje;
	private LinkedList next = null;
	
	LinkedList(Object _obje, LinkedList _next){
		this.obje = _obje;
		this.next = _next;
	}
	LinkedList(Object _obje){
		this.obje = _obje;
	}
	
	public void DispLink(){
		if(this.next != null){
			System.out.println(this.obje.toString());
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
	public Object getObject(){
		return this.obje;
	}
	public void setObject(Object _obje){
		this.obje = _obje;
	}
	
	public LinkedList getNextList(){
		return this.next;
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
