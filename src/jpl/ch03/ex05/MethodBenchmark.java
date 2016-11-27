package jpl.ch03.ex05;

/***
 * 他のベンチマークを行う新たな”拡張したクラス”を作成
 * @author murase
 *
 */

public class MethodBenchmark extends Benchmark{
	//ベンチマークの実装(オーバーライド)	
	void benchmark(){
		
	}
	/*
	public static void main(String[] args){
		int count = Integer.parseInt(args[0]);										//反復回数
		long time = new MethodBenchmark().repeat(count);						//スーパークラスのrepeatメソッドを実行
		System.out.println(count + " methods average in " + time + "nanoseconds");	//表示
	}
	*/
	
}
