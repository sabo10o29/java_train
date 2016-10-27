package jpl.ch01.ex13;

public class ex13 {

	static final int MAX_INDEX = 9;

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		int lo = 1;
		int hi = 1;

		String mark;
		System.out.println("1: "+lo);

		for(int i = 2; i<= MAX_INDEX; i++){
			if(hi % 2 == 0){
				mark = " *";
			}else{
				mark = "";
			}
			System.out.printf("%d: %d%s%n",i,hi,mark);
			hi = lo + hi;	//新しいhi
			lo = hi - lo;	/*新しいloは、（合計-古いlo）
			 				　すなわち、古いhi*/
		}
	}

}
