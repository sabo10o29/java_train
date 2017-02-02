package jpl.ch12.ex01;

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
	
	public LinkedList<E> find(String name) throws ObjectNotFoundException{
		System.out.println("Now object: " + element.toString() + "Find object: " + name);
		LinkedList<E> result = null;
		
		//ターゲットだった場合の処理
		if(name.equals(element.toString())){
			return this;
		}else{
			if(next!=null){
				//次のリストが存在する場合
				result = next.find(name);
			}else{
				//次がないうえに、ターゲットが存在しない場合
				throw new ObjectNotFoundException(name);
			}
		}
		
		return result;
		
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
