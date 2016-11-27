package jpl.ch03.ex05;

import static org.junit.Assert.*;

import org.junit.Test;

public class RepeatBenchmarkTest {

	@Test
	public void testRepeatAverage() {
		//カウント回数が0のときに計測時間が0を返すかテスト
		int count = Integer.parseInt("0");											//反復回数
		long time = new RepeatBenchmark().repeatAverage(count);						//スーパークラスのrepeatメソッドを実行
		assertEquals(time,0);
	}

}
