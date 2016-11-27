package jpl.ch06.ex02;

/***
 * Vehicleクラスにturnメソッドを追加する
 * 一つは入力値で回転、2つ目は定数値で回転するメソッドをオーバーロードによって実現する
 * @author murase
 *
 */

public class AddTurnVehicle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle car = new Vehicle(5,"Yoshikazu Murase",250);
		car.turn(20);
		car.showInfo();
		car.turn(Vehicle.Turn.TURN_LEFT.value);
		car.showInfo();
		
	}

}
