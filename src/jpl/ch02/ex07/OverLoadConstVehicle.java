package jpl.ch02.ex07;

/***
 * コンストラクタをオーバーロード
 * @author murase
 *
 */

public class OverLoadConstVehicle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle test = new Vehicle(0,"Yoshikazu",0);
		Vehicle test2 = new Vehicle();
		test.showInfo();
		test2.showInfo();
		
	}

}
