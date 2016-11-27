package jpl.ch03.ex10;

public class LinkedList implements Cloneable{
	private Object obje;
	private LinkedList next = null;
	
	//メインメソッド
	public static void main (String[] args){
		// リストが参照しているオブジェクトに対する変更は、他方のリストから見える
        LinkedList link1 = new LinkedList(new Vehicle(100,"Murase",340));

        link1.setNextLinkedList(new LinkedList(new Vehicle(50, "Tanaka",30)));

        LinkedList link2 = new LinkedList(null);
        link2 = link1.clone();

        System.out.println(((Vehicle) link1.getObject()).getSpeed());
        System.out.println(((Vehicle) link2.getObject()).getSpeed());

        System.out.println("");

        // link1からもlink2からも変更が見える
        ((Vehicle)link2.getObject()).changeSpeed(160);;

        System.out.println(((Vehicle)link1.getObject()).getSpeed());
        System.out.println(((Vehicle)link2.getObject()).getSpeed());

        // １つのリストに対する変更は、他方のリストに影響しない（link2のリストを別の参照にする）
        link2 = new LinkedList(new Vehicle(45,"Yamada",600));

        link2.setNextLinkedList(new LinkedList(new Vehicle(15,"Hayashi",120)));

        System.out.println("");
        System.out.println(((Vehicle) link1.getObject()).getSpeed());
        System.out.println(((Vehicle) link2.getObject()).getSpeed());
	}
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
