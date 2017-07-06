package jpl.ch01.ex09;

/**
 * フィボナッチ数列を配列に保存し、最後に値を表示するプログラム
 * @author murase
 */

public class SysFibonacci {

	public static void main(String args[]){
		System.out.println("フィボナッチ数列");
		Integer fib[] = new Integer[20];
		int lo = 1;
		int hi = 1;
		int i = 0;

		System.out.println("1: "+lo);
		while(hi < 50){
			fib[i] = hi;
			hi = lo + hi;	//新しいhi
			lo = hi - lo;	/*新しいloは、（合計-古いlo）
			 				　すなわち、古いhi*/
			i++;
		}
		for(int j=0;j<i;j++){
			System.out.println((j+2)+": "+fib[j]);
		}

	}
}
