package jpl.ch03.ex05;

/***
 * 抽象クラス
 * benchmarkを実装する必要がある
 * @author murase
 *
 */
abstract class Benchmark {
	abstract void benchmark();
	
	public final long repeat(int count){
		long start = System.nanoTime();			//開始時刻記録用変数
		for(int i = 0; i < count; i++){			//反復回数まで行ったときの処理時間の計測
			benchmark();
		}
		return (System.nanoTime() - start);		//経過時間を返す
	}
}
