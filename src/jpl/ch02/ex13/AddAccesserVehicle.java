package jpl.ch02.ex13;
import jpl.ch02.ex13.Vehicle;

/***
 * Vehicleクラスのフィールドにたいするアクセッサ-メソッドを追加
 * スピードと角度は常に変化し続けるため、ゲッターだけでなくセッターも必要になる
 * IDとオーナーに関しては、不変であるためセッターは必要ない
 * @author murase
 *
 */

public class AddAccesserVehicle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle car = new Vehicle(30, "Yoshikazu Murase",100);
		
		System.out.println("ID: " + car.getID());
		System.out.println("Owner: " + car.getOwner());
		System.out.println("Angle: " + car.getAngle());
		System.out.println("Speed: " + car.getSpeed());
		
		car.setAngle(90);
		car.setSpeed(300);
		
		System.out.println("ID: " + car.getID());
		System.out.println("Owner: " + car.getOwner());
		System.out.println("Angle: " + car.getAngle());
		System.out.println("Speed: " + car.getSpeed());
	}

}
