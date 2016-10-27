package jpl.ch02.ex07;

public class ex07 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//格納する乗り物リスト
		Vehicle[] cars = {
				new Vehicle(2,"matsuda"),
				new Vehicle(3,"toyota"),
				new Vehicle(20,"honda"),
				new Vehicle(20,"kawasaki"),
				new Vehicle(20,"suzuki"),
				new Vehicle(20,"nissan"),
				new Vehicle(20,"yamaha"),
				new Vehicle()
		};
		
		LinkedList last = new LinkedList(cars[0]);
		LinkedList before;
	
		LinkedList list = new LinkedList(cars[1], last);
		for(int i=2; i<cars.length; i++){
			before = list;
			list = new LinkedList(cars[i], before);
		}
		list.DispLink();
	}

}
