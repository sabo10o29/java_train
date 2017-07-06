package jpl.ch22.ex02;

import java.util.HashSet;

public class WhichChars {

	private HashSet<Character> used = new HashSet<Character>();

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
