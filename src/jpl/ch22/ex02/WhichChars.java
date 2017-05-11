package jpl.ch22.ex02;

import java.util.BitSet;
import java.util.HashSet;

public class WhichChars {

//	private BitSet used = new BitSet();
	private HashSet<Character> used = new HashSet<Character>();
	
	public WhichChars(String str){
		for(int i = 0; i < str.length(); i++){
//			used.set(str.charAt(i));	//文字に対応したビットを指定する。
			used.add(str.charAt(i));
		}
	}
	
	public String toString(){
		String desc = "[";
//		for(int i = used.nextSetBit(0); i>=0; i = used.nextSetBit(i+1)){
//			desc += (char) i;
//		}
		for(Character c : this.used){
			desc += (char) c;
		}
		return desc + "]";
	}
	
	public static void main(String[] args){
		
		WhichChars test = new WhichChars("Testing 1 2 3");
		System.out.println(test);
	}
	
	
}
