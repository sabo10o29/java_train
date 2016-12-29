package jpl.ch10.ex01;

public class ChangeStr {

	public static void main(String[] args) {
		
		String[] a = replaceStr(args);
		
	}
	
	public static String[] replaceStr(String[] str){
		
		if(str.length!=0){
			for(int i=0; i<str.length; i++){
				str[i] = str[i].replaceAll("!", "?");
			}
		}else{
			System.out.println("input error");
		}
		
		for(int i=0; i<str.length; i++){
			System.out.println(str[i]);
		}
		return str;
		
	}
	
	

}
