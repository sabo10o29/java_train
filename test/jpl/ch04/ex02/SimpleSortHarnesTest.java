package jpl.ch04.ex02;

import static org.junit.Assert.*;

import org.junit.Test;

public class SimpleSortHarnesTest {
	
	//SortableインターフェースをインプリメントしているSortHarnes抽象クラスを拡張しているSimpleSortHarnesTest上でテスト
	@Test
	public void testDoSort() {
		SimpleSortHarnes test = new SimpleSortHarnes();
		Object[] testData = { "b", "c", "a", "d" };			//入力データ
		test.sort(testData);								//ソート
		assertEquals(test.resultValue()[0].toString(),"a");
		assertEquals(test.resultValue()[1].toString(),"b");
		assertEquals(test.resultValue()[2].toString(),"c");
		assertEquals(test.resultValue()[3].toString(),"d");
	}

}
