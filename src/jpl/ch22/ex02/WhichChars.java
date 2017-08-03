package jpl.ch22.ex02;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;


public class WhichChars {
	
	//元の並びを保持するためにはツリーセットを用いる
	private TreeSet<Character> used = new TreeSet<Character>();

	public WhichChars(String str) {
		for (int i = 0; i < str.length(); i++) {
			used.add(str.charAt(i));
		}
	}

	public String toString() {
		String desc = "[";
		for (Character c : this.used) {
			desc += (char) c;
		}
		return desc + "]";
	}

	public static void main(String[] args) {

		WhichChars test = new WhichChars("Testing 1 2 3");
		System.out.println(test);
	}

}
