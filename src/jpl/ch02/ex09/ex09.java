package jpl.ch02.ex09;

public class ex09 {

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
		System.out.println(Vehicle.maxID());
		
		
		
	}

}
