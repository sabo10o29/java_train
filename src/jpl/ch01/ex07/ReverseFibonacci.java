package jpl.ch01.ex07;

/**
 * iが逆になるように書き直すプログラム
 * @author murase
 */

public class ReverseFibonacci {

	static final int MAX_INDEX = 9;
	public static void main(String args[]){
		int lo = 1;
		int hi = 1;

		String mark;


		for(int i = MAX_INDEX; i>= 2; i--){
			if(hi % 2 == 0){
				mark = " *";
			}else{
				mark = "";
			}
			System.out.println(i+": "+hi+mark);
			hi = lo + hi;	//新しいhi
			lo = hi - lo;	/*新しいloは、（合計-古いlo）
			 				　すなわち、古いhi*/
		}
		System.out.println("1: "+lo);
	}
}
