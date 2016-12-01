package jpl.ch03.ex11;

import static org.junit.Assert.*;

import org.junit.Test;

public class SortDoubleTest {

	@Test
	public void testSort() {
		//入力データを書き換えても値が変化しないか確認
		SimpleSortDouble test = new SimpleSortDouble();		//
		double[] testData = { 0.3, 1.3e-2, 7.9, 3.17 };		//入力データ
		test.sort(testData);								//ソート
		testData[0] = 100;									//入力データの改竄
		assertNotEquals(testData[0],test.resultValue()[0]);
	}

	@Test
	public void testResultValue() {
		//ソートした値を出力するかチェック
		SimpleSortDouble test = new SimpleSortDouble();		//
		double[] testData = { 0.3, 1.3e-2, 7.9, 3.17 };		//入力データ
		test.sort(testData);								//ソート
		testData[0] = 100;									//入力データの改竄
		Double[] ansData = { 1.3e-2, 0.3, 3.17, 7.9 };		//回答データ
		assertTrue(ansData[0].equals(test.resultValue()[0]));
		assertTrue(ansData[1].equals(test.resultValue()[1]));
		assertTrue(ansData[2].equals(test.resultValue()[2]));
		assertTrue(ansData[3].equals(test.resultValue()[3]));
		
	}

}
