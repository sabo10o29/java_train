package jpl.ch01.ex07;

public class ex07 {

	static final int MAX_INDEX = 9;

	/**
	 * 偶数要素に'＊'をつけて、フィボナッチ数列の
	 * 最初の方の要素を表示する
	 * @param args
	 */
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
