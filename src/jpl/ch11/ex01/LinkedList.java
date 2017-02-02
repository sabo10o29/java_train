package jpl.ch11.ex01;

public class LinkedList<E> {
	private E element;
	private LinkedList<E> next = null;
	
	LinkedList(E _element, LinkedList<E> _next){
		this.element = _element;
		this.next = _next;
	}
	LinkedList(E _element){
		this.element = _element;
	}
	
	//ゲッターセッター
	public E getElement(){
	return this.element;
	}
	public void setObject(E _element){
		this.element = _element;
	}
	public LinkedList<E> getNextLinkedList(){
		return this.next;
	}
	public void setNextLinkedList(LinkedList<E> _next){
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
