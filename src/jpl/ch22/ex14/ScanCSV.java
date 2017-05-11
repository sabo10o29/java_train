package jpl.ch22.ex14;

import sun.util.locale.StringTokenIterator;

//
public class ScanCSV {

	public static double sumVals(String str) {

		StringTokenIterator st = new StringTokenIterator(str, " ");
		double result = Double.parseDouble(st.current());
		while (st.hasNext()) {
			
			String s = st.next();
			result += Double.parseDouble(s);

		}
		return result;

	}

	public static void main(String[] args) {
		String str = "2.3432 33.43324 0.2344";
		System.out.println("合計値：" + ScanCSV.sumVals(str));
	}

}
