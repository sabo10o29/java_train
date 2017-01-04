package jpl.ch10.ex05;

public class ListChar {

	public static void main(String[] args) {
		ListChar.listchar('a', '*');

	}
	
	public static void listchar(char a, char b){
		Integer A = (int)a;
		Integer B = (int)b;
		if(A<B){
			for(int i=A;i<=B;i++){
				System.out.print(Character.toChars(i));
			}
		}else{
			for(int i=B;i<=A;i++){
				System.out.print(Character.toChars(i));
			}
		}
		
	}
	
	

}
