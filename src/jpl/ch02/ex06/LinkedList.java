package jpl.ch02.ex06;

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
}
