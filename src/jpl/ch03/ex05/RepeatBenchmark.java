package jpl.ch03.ex05;

public class RepeatBenchmark extends Benchmark{
	//ベンチマークの実装(オーバーライド)	
	void benchmark(){
		
	}
	
	public static void main(String[] args){
		int count = Integer.parseInt(args[0]);										//反復回数
		long time = new RepeatBenchmark().repeatAverage(count);						//スーパークラスのrepeatメソッドを実行
		System.out.println(count + " methods average in " + time + "nanoseconds");	//表示
	}
	
	//平均を算出するメソッド
	public final long repeatAverage(int count){
		long start = System.nanoTime();			//開始時刻記録用変数
		for(int i = 0; i < count; i++){			//反復回数まで行ったときの処理時間の計測
			benchmark();
		}
		if(count!=0){
			return ((System.nanoTime() - start)/count);		//経過時間を返す
		}else{
			return 0;		//経過時間を返す
		}
		
	}
	
}
