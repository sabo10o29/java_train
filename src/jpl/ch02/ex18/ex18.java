package jpl.ch02.ex18;
import jpl.ch02.ex18.Vehicle;

/***
 * コマンドラインで入力された文字列を所有者としてVehicleクラスのインスタンスを生成するプログラム
 * @author murase
 *
 */

public class ex18 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle car = new Vehicle(5,args[0],250);
		car.turn(20);
		car.showInfo();
		car.turn(Vehicle.TURN_RIGHT);
		car.showInfo();
	}

}
