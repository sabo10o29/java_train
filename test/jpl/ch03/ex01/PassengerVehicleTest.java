package jpl.ch03.ex01;

import static org.junit.Assert.*;

import org.junit.Test;
import StdCap.StdoutCapture;
import jpl.ch03.ex01.PassengerVehicle;

//未作成
public class PassengerVehicleTest {

	//幾つかのインスタンスを生成し、フィールド値を表示することができるかテスト
	@Test
	public void testMain() {
		String[] expected = new String[] 
				{"Seats: 2, Passenger: 0",
				"Seats: 8, Passenger: 7",
				"Seats: 4, Passenger: 3"};
        
        StdoutCapture sc = new StdoutCapture();
        sc.start();
        
        PassengerVehicle.main(new String[0]);
        
        sc.stop();
        sc.assertEquals(expected);
	}

	//拡張したフィールド値を含むオブジェクトが正しく生成されるかテスト
	@Test
	public void testPassengerVehicle() {
		PassengerVehicle vehicle = new PassengerVehicle("Murase",2);
		assertNotNull(vehicle);
		
	}

	//正しいフィールド値を返すかテスト
	@Test
	public void testSeats() {
		PassengerVehicle vehicle = new PassengerVehicle("Murase",2);
		assertEquals(vehicle.seats(),2);
	}

	//正しいフィールド値を返すかテスト
	@Test
	public void testNumPassenger() {
		PassengerVehicle vehicle = new PassengerVehicle("Murase",2);
		vehicle.setPassengers(1);
		assertEquals(vehicle.numPassenger(),1);
	}

	@Test
	public void testSetPassengers() {
		//範囲内の値の場合に正しく値が代入されるかテスト
		PassengerVehicle vehicle = new PassengerVehicle("Murase",2);
		vehicle.setPassengers(1);
		assertEquals(vehicle.numPassenger(),1);
		//範囲外の値の場合に正しく値が代入されるかテスト
		vehicle.setPassengers(3);					//範囲外の値の場合には値が設定されない
		assertEquals(vehicle.numPassenger(),1);
	}
	

}
