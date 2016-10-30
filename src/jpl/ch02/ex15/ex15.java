package jpl.ch02.ex15;

/***
 * スピードを0にするメソッドとスピードを変化させるメソッドを追加する
 * @author murase
 *
 */

public class ex15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle car = new Vehicle(30, "Yoshikazu Murase",200);
		System.out.println("Speed: "+car.getSpeed());
		car.changeSpeed(100);
		System.out.println("Speed: "+car.getSpeed());
		car.stop();
		System.out.println("Speed: "+car.getSpeed());
		
		
	}

}
