package jpl.ch09.ex02;

public class BitCount {

	public static void main(String[] args) {
		BitCount test = new BitCount(67);

	}
	
	public BitCount(int x){
		String str = Integer.toBinaryString(x);
		System.out.println(str);
		
		int count = 0; 
		for(char c: str.toCharArray()){
			if(c=='1'){
				count++;
			}
		}
		System.out.println("Number of '1' is "+count);
		
	}

}
