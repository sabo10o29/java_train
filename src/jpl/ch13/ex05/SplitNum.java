package jpl.ch13.ex05;

public class SplitNum {
	
	
	public static String splitnum(String _str, char split, int interval){
		int num = _str.length()/interval;
		if(_str.length()%interval==0)num--;
		
		StringBuffer str = new StringBuffer(_str);
		str = str.reverse();
		
		int i=0;
		while(i!=num){
			str.insert((interval+1)*i+interval, split);
			i++;
		}
		return new String(str.reverse());
	}
	public static void main(String[] args){
		System.out.println(SplitNum.splitnum("1543729", ',', 3));
	}
	
}
