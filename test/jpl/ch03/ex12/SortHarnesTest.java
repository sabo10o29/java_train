package jpl.ch03.ex12;

import static org.junit.Assert.*;

import org.junit.Test;

import jpl.ch03.ex11.SimpleSortDouble;

public class SortHarnesTest {

	@Test
	public void testSort() {
		SimpleSortHarnes test = new SimpleSortHarnes();		//
		Object[] testData = { "b", "c", "a", "d" };			//入力データ
		test.sort(testData);								//ソート
		//オブジェクトの名前を使用してソートされるかテスト
		assertEquals(test.resultValue()[0].toString(),"a");
		assertEquals(test.resultValue()[1].toString(),"b");
		assertEquals(test.resultValue()[2].toString(),"c");
		assertEquals(test.resultValue()[3].toString(),"d");
	}

	@Test
	public void testResultValue() {
		SimpleSortHarnes test = new SimpleSortHarnes();		//
		Object[] testData = { "b", "c", "a", "d" };			//入力データ
		test.sort(testData);								//ソート
		//入力データを改竄してもフィールド値が変更されないかチェック
		testData[0] = "e";
		assertNotEquals(test.resultValue()[0].toString(),"e");
	}

}
