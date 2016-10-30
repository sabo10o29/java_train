package jpl.ch01.ex10;

/**
 * フィボナッチ数列を配列に保存する
 * 数列の値と偶数か奇数かをブール値としてもつクラス
 * クラスへの参照を配列としてもつ
 * 以上の条件を満たす
 * @author murase
 */

public class ex10 {

	static final int MAX_INDEX = 9;

	public static void main(String args[]){
		int lo = 1;
		int hi = 1;
		SaveEven[] FibArray = new SaveEven[MAX_INDEX];
		String mark;
		
		System.out.println("1: "+lo);
		FibArray[0] = new SaveEven(lo,false);
		for(int i = 2; i<= MAX_INDEX; i++){
			if(hi % 2 == 0){
				FibArray[i-1] = new SaveEven(hi,true);
				mark = " *";
			}else{
				mark = "";
				FibArray[i-1] = new SaveEven(hi,false);
			}
			//System.out.println(i+": "+hi+mark);
			hi = lo + hi;	//新しいhi
			lo = hi - lo;	/*新しいloは、（合計-古いlo）
			 				　すなわち、古いhi*/
		}
		
		for(int i=0; i<MAX_INDEX; i++){
			FibArray[i].dispFib();
		}

	}
}
