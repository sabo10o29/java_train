package jpl.ch02.ex14;

public class ex14 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Vehicle(20,"Murase");
		
		LinkedList list = new LinkedList(new Vehicle(30,"Yoshikazu Murase"), null);
		
		System.out.println("Object: " + list.getObject().toString());
		System.out.println("Owner: " + list.getNextList());
		
		list.setObject(new Vehicle(30,"Tanaka Tarou"));
		
		System.out.println("Object: " + list.getObject().toString());
		System.out.println("Owner: " + list.getNextList());

	}

}
