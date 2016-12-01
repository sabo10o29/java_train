package jpl.ch03.ex08;

import static org.junit.Assert.*;

import org.junit.Test;

public class PassengerVehicleTestTest {

	@Test
	public void testClone() {
		//車インスタンスの作成
		PassengerVehicle car2 = new PassengerVehicle("Murase", 2);
		//インスタンスの設定（初期化）
		car2.chargeBattery(1234);	//バッテリー	：100
		car2.fillGastank(5678);		//ガスタンク	：100
		car2.ridePassenger();		//乗員		：100
		//car1のクローンの作成
		PassengerVehicle clone_car2 = car2.clone();
		//car1フィールド値の変更→clone_car1に変更が反映されないかどうか確認するため
		car2.dropPassenger();		//乗員		：1
		car2.consumeBattery(234);	//バッテリー	：1000
		car2.consumeGastank(678);	//ガスタンク	：5000
		//car1とclone_car1で値が異なっているか確認
		assertNotEquals(car2.numPassenger(),clone_car2.numPassenger());
		assertNotEquals(car2.battery(),clone_car2.battery());
		assertNotEquals(car2.gasTank(),clone_car2.gasTank());
		
		
	}

}
