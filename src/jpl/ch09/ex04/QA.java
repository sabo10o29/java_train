package jpl.ch09.ex04;

public class QA {

	public static void main(String[] args) {
		//(1) 3 << 2L - 1
		//・・・00011を1ビット左シフト
		//・・000110
		//6
		System.out.println((3 << 2L - 1));
		
		//(2) (3L << 2) - 1
		//・・・0011を左に2シフト
		//・・・001100
		//12-1　＝11
		System.out.println(((3L << 2) - 1));
		
		//(3) 10 < 12 == 6 >> 17
		//エラー=booleanと数値の比較になるため
		
		//(4) 10 << 12 == 6 >> 17
		//01010 << 12, 00110 >> 17
		//01010000000000000 == 000000000000000
		//false
		System.out.println((10 << 12 == 6 >> 17));
		
		//(5) 13.5e-1 % Float.POSITIVE_INFINITY
		//1.35 % Float.POSITIVE_INFINITY
		//1.35
		System.out.println((13.5e-1 % Float.POSITIVE_INFINITY));
		
		//(6) Float.POSITIVE_INFINITY + Double.NEGATIVE_INFINITY
		//NaN
		System.out.println((Float.POSITIVE_INFINITY + Double.NEGATIVE_INFINITY));
		
		//(7) Double.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY
		//+∞
		System.out.println((Double.POSITIVE_INFINITY - Float.NEGATIVE_INFINITY));
		
		//(8) 0.0 / -0.0 == -0.0 /0.0
		//true
		System.out.println((0.0 / -0.0 == -0.0 /0.0));
		//0.0/0.0はNaNとなり、NaNの比較は無条件でfalseとなるため
		
		//(9) Integer.MAX_VALUE + Integer.MIN_VALUE
		//255 - 256 = 1
		System.out.println((Integer.MAX_VALUE + Integer.MIN_VALUE));
		
		//(10) Long.MAX_VALUE + 5 
		//Error　☓
		System.out.println((Long.MAX_VALUE + 5));
		
		
		//(11) (Short) 5 * (byte) 10
		//50
		System.out.println(((short) 5 * (byte) 10));
		
	}
	

}
