package jpl.ch01.ex06;

public class ex06 {
	static final String title = "フィボナッチ数列";
	static final int MAX = 50;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println(title);
		int lo = 1;
		int hi = 1;
		System.out.println(lo);
		while(hi < MAX){
			System.out.println(hi);
			hi = lo + hi;	//新しいhi
			lo = hi - lo;	/*新しいloは、（合計-古いlo）
			 				　すなわち、古いhi*/
		}
	}

}
