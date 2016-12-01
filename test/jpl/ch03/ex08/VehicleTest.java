package jpl.ch03.ex08;

import static org.junit.Assert.*;

import org.junit.Test;

public class VehicleTest {

	@Test
	public void testClone() {
		//車インスタンスの作成
		Vehicle car1 = new Vehicle("Tanaka");
		//インスタンスの設定（初期化）
		car1.changeSpeed(100);		//スピード	：100
		car1.chargeBattery(120);	//バッテリー	：120
		car1.fillGastank(1000);		//ガスタンク	：1000
		//car1のクローンの作成
		Vehicle clone_car1 = car1.clone();
		//car1フィールド値の変更→clone_car1に変更が反映されないかどうか確認するため
		car1.changeSpeed(340); 		//スピード	：340
		car1.consumeBattery(30);	//バッテリー	：70
		car1.consumeGastank(500);	//ガスタンク	：500
		//car1とclone_car1で値が異なっているか確認
		assertNotEquals(car1.getSpeed(),clone_car1.getSpeed());
		assertNotEquals(car1.battery(),clone_car1.battery());
		assertNotEquals(car1.gasTank(),clone_car1.gasTank());	
	}

}
