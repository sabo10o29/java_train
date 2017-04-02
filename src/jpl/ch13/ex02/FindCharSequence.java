package jpl.ch13.ex02;

public class FindCharSequence {
	
	public static int find(String target, String find){
		
		int count = 0;
		int i = 0;
		if(target.length()>find.length()){
			while(i<(target.length())){
				if(target.regionMatches(i, find, 0, find.length()))count++;
				i=i+find.length();
			}
			return count;
		}else{
			return 0;
		}
				
		
	}
	
	public static void main(String[] args){
		System.out.println(FindCharSequence.find("aa", "aa"));
	}
	
}
