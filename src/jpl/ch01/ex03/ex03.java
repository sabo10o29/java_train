package jpl.ch01.ex03;

/**
 * フィボナッチにタイトルを出力するプログラム
 * @author murase
 */

public class ex03 {
	/**値が50未満のフィボナッチ数列を表示する*/
	public static void main(String[] args) {
		System.out.println("フィボナッチ数列");
		int lo = 1;
		int hi = 1;
		System.out.println(lo);
		while(hi < 50){
			System.out.println(hi);
			hi = lo + hi;	//新しいhi
			lo = hi - lo;	/*新しいloは、（合計-古いlo）
			 				　すなわち、古いhi*/
		}

	}

}
