package gui.ex21;

import java.util.Calendar;

/**
 * 時計の文字クラス：現在の時刻を取得してString型で返す。
 * @author YoshikazuMurase
 *
 */
public class CurrentStringTime {
	
	public static String[] getFullTime(){
		String[] strs = new String[11];
		Calendar cal = Calendar.getInstance();
		strs[0] = CurrentStringTime.getSec(cal)[0];
		strs[1] = CurrentStringTime.getSec(cal)[1];
		strs[2] = CurrentStringTime.getMin(cal)[0];
		strs[3] = CurrentStringTime.getMin(cal)[1];
		strs[4] = CurrentStringTime.getHour(cal)[0];
		strs[5] = CurrentStringTime.getHour(cal)[1];
		strs[6] = CurrentStringTime.getDay(cal)[0];
		strs[7] = CurrentStringTime.getDay(cal)[1];
		strs[8] = CurrentStringTime.getMonth(cal)[0];
		strs[9] = CurrentStringTime.getMonth(cal)[1];
		strs[10] = CurrentStringTime.getYear(cal);
		return strs;
	}
	public static String[] getSec(){
		return tenDigitStrings(Calendar.getInstance().get(Calendar.SECOND));
	}
	public static String[] getMin(){
		return tenDigitStrings(Calendar.getInstance().get(Calendar.MINUTE));
	}
	public static String[] getHour(){
		return tenDigitStrings(Calendar.getInstance().get(Calendar.HOUR));
	}
	public static String[] getDay(){
		return tenDigitStrings(Calendar.getInstance().get(Calendar.DATE));
	}
	public static String[] getMonth(){
		return tenDigitStrings(Calendar.getInstance().get(Calendar.MONTH));
	}
	public static String getYear(){
		return String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
	}
	
	public static String[] getSec(Calendar cal){
		return tenDigitStrings(cal.get(Calendar.SECOND));
	}
	public static String[] getMin(Calendar cal){
		return tenDigitStrings(cal.get(Calendar.MINUTE));
	}
	public static String[] getHour(Calendar cal){
		return tenDigitStrings(cal.get(Calendar.HOUR));
	}
	public static String[] getDay(Calendar cal){
		return tenDigitStrings(cal.get(Calendar.DATE));
	}
	public static String[] getMonth(Calendar cal){
		return tenDigitStrings(cal.get(Calendar.MONTH));
	}
	public static String getYear(Calendar cal){
		return String.valueOf(cal.get(Calendar.YEAR));
	}
	//10桁のフォントに変更するメソッド
	//1桁の場合には10桁に変更する
	private static String[] tenDigitStrings(int _number){
		String[] strs = new String[2];
		//一桁目：１０で割ったあまりが入る
		strs[0] = String.valueOf(((int)_number%10));
		//二桁目：１０で割った商が入る
		strs[1] = String.valueOf(((int)_number/10));
		return strs;
	}
}
	
	
