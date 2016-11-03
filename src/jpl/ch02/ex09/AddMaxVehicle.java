package jpl.ch02.ex09;

/***
 * IDの最大値をかえすメソッドをVehicleクラスに追加
 * @author murase
 *
 */

public class AddMaxVehicle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle[] cars = {
				new Vehicle(2,"matsuda",100),
				new Vehicle(3,"toyota",200),
				new Vehicle(20,"honda",300),
				new Vehicle(20,"kawasaki",222),
				new Vehicle(20,"suzuki",344),
				new Vehicle(20,"nissan",234),
				new Vehicle(20,"yamaha",345)
		};
		System.out.println(Vehicle.maxID());
		
		
		
	}

}
