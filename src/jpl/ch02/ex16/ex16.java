package jpl.ch02.ex16;

public class ex16 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle[] cars = {
				new Vehicle(2,"matsuda"),
				new Vehicle(3,"toyota"),
				new Vehicle(20,"honda"),
				new Vehicle(20,"kawasaki"),
				new Vehicle(20,"suzuki"),
				new Vehicle(20,"nissan"),
				new Vehicle(20,"yamaha")
		};
		
		LinkedList last = new LinkedList(cars[0]);
		LinkedList before;
	
		LinkedList list = new LinkedList(cars[1], last);
		for(int i=2; i<cars.length; i++){
			before = list;
			list = new LinkedList(cars[i], before);
		}
		System.out.println("Number of cars: "+list.getNumList());
		
		
	}

}
