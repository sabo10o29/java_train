package jpl.ch13.ex01;

public class FindChar {
	
	public static int find(String target, char find){
		
		int count = 0;
		int i = 0;
		if(target.length()>0){
			while(i<target.length()){
				if(target.charAt(i)==find)count++;
				i++;
			}
			return count;
		}else{
			return 0;
		}
				
		
	}
	
	public static void main(String[] args){
		System.out.println(FindChar.find("aiueoeoeoe", 'e'));
	}
	
}
