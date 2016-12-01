package jpl.ch03.ex09;

import static org.junit.Assert.*;

import org.junit.Test;

import Hello.HelloWorld;
import StdCap.StdoutCapture;

public class GarageTest {

	@Test
	public void testMain() {

	}

	@Test
	public void testGarage() {
		Garage test = new Garage();
		assertNotNull(test);
	}

	@Test
	public void testClone() {
		Garage garage = new Garage();					//複製元インスタンス
		Garage clone_garage;							//複製インスタンス
		//複製元の初期化
		garage.loading(new Vehicle("Murase"));
		garage.loading(new Vehicle("Tanaka"));
		garage.arrayVehicle[0].chargeBattery(100);
		garage.arrayVehicle[0].fillGastank(500);
		garage.arrayVehicle[0].changeSpeed(340);
		//現時点の状態を複製
		clone_garage = garage.clone();
		//複製元の情報を変更
		garage.unloading();
		garage.arrayVehicle[0].consumeBattery(20);
		garage.arrayVehicle[0].consumeGastank(100);
		garage.arrayVehicle[0].changeSpeed(111);
		assertNotEquals(garage.arrayVehicle[0].gasTank(),clone_garage.arrayVehicle[0].gasTank());
		assertNotEquals(garage.arrayVehicle[0].battery(),clone_garage.arrayVehicle[0].battery());
		assertNotEquals(garage.arrayVehicle[0].getSpeed(),clone_garage.arrayVehicle[0].getSpeed());
	}

	@Test
	public void testLoading() {
		Garage garage = new Garage();
		garage.loading(new Vehicle("Murase"));
		garage.loading(new Vehicle("Tanaka"));
		assertNotNull(garage.arrayVehicle[1]);		//配列に2台目の車が入庫されているかチェック
	}

	@Test
	public void testUnloading() {
		Garage garage = new Garage();
		garage.loading(new Vehicle("Murase"));
		garage.loading(new Vehicle("Tanaka"));
		garage.unloading();
		assertNull(garage.arrayVehicle[1]);			//二つ目の車が削除されているかチェック
	}

}
