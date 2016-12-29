package jpl.ch10.ex02;

public class ChangeStr {

	public static void main(String[] args) {
		
		String[] a = replaceStr(args);
		
	}
	
	public static String[] replaceStr(String[] str){
		
		switch(str.length){
			case 0:
				System.out.println("input error");
			default:
				for(int i=0; i<str.length; i++){
					str[i] = str[i].replaceAll("!", "?");
				}
				break;
		}
		
		for(int i=0; i<str.length; i++){
			System.out.println(str[i]);
		}
		return str;
		
	}
	
	

}