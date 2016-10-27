package jpl.ch01.ex10;

public class ex10 {

	static final int MAX_INDEX = 9;

	public static void main(String args[]){
		int lo = 1;
		int hi = 1;
		boolean even[];
		Integer fib[] = new Integer[20];
		String mark;
		SaveEven saveeven = new SaveEven();

		saveeven.checkEven(lo);
		fib[0] = lo;
		System.out.println("1: "+lo);
		for(int i = 2; i<= MAX_INDEX; i++){
			fib[i-1] = hi;
			saveeven.checkEven(hi);
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
		even = saveeven.geteven();

	}
}
