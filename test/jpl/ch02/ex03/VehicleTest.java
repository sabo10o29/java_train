package jpl.ch02.ex03;

import static org.junit.Assert.*;
import org.junit.Test;
import StdCap.StdoutCapture;

public class VehicleTest {
	
	//インスタンスが正しく生成されているかどうか確認するテスト
	@Test
	public void testVehicle() {
		Vehicle test = new Vehicle(10,"Yoshikazu Murase",60);
		assertNotNull(test);
		//fail("Not yet implemented");
	}

	
	//値が正しく出力されているかのテスト（VehicleメソッドのshowInfo関数を用いて期待される文字列と比較を行う）
	@Test
	public void testShowInfo() {
		Vehicle test = new Vehicle(10,"Yoshikazu Murase",60);
		String[] expected = new String[] {"Owner: Yoshikazu Murase","ID: 2","Angle: 10.0, Speed: 60"};
		
		StdoutCapture sc = new StdoutCapture();
        sc.start();
        
		test.showInfo();
		
		sc.stop();
        sc.assertEquals(expected);
		
		//fail("Not yet implemented");
	}

}
