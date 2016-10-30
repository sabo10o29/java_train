package jpl.ch01.ex12;

/**
 * Stringオブジェクトを作成して表示するプログラム
 * @author murase
 *
 */

public class ex12 {

	static final int MAX_INDEX = 9;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int lo = 1;
		int hi = 1;

		String mark;
		System.out.println("1: "+lo);
		String str;

		for(int i = 2; i<= MAX_INDEX; i++){
			if(hi % 2 == 0){
				mark = " *";
			}else{
				mark = "";
			}
			str = i+": "+hi+mark;
			System.out.println(str);
			hi = lo + hi;	//新しいhi
			lo = hi - lo;	/*新しいloは、（合計-古いlo）
			 				　すなわち、古いhi*/
		}

	}

}
