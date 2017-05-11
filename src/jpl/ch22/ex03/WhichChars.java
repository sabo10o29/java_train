package jpl.ch22.ex03;

import java.util.BitSet;
import java.util.HashMap;

public class WhichChars {

	private HashMap<Byte, BitSet> map = new HashMap<Byte, BitSet>();
	
	public WhichChars(String str){
		for(int i = 0; i < str.length(); i++){
			char c = str.charAt(i);
			Byte b1 = (byte) ((c >>> 8) & 0xFF);
			Byte b2 = (byte) ( c & 0xFF);
			BitSet under = new BitSet();
			under.set(b2);
			this.map.put(b1, under);
			System.out.println(i+"番目の文字");
			System.out.println("上位：　"+Integer.toBinaryString(b1));
			System.out.println("下位：　"+Integer.toBinaryString(b2));
		}
	}
	
	public static void main(String[] args){
		WhichChars test = new WhichChars("あかgででddedえd");
	}
	
	
}
