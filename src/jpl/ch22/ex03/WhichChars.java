package jpl.ch22.ex03;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;


public class WhichChars {
	private HashMap<Byte, BitSet> map = new HashMap<Byte, BitSet>();

	public WhichChars(String str) {
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			byte top = (byte) (c >> 8);
			byte btm = (byte) (c & 0xFF);
			BitSet bs = map.get(top);
			if (bs == null)
				bs = new BitSet();
			bs.set(btm);
			this.map.put(top, bs);
		}
	}

	public String toString() {
		String desc = "[";
		for (Entry<Byte, BitSet> e : map.entrySet()) {
			byte top = (Byte) e.getKey();
			BitSet bs = (BitSet) e.getValue();
			for (int i = bs.nextSetBit(0); i >= 0; i = bs.nextSetBit(i + 1)) {
				desc += (char) ((top << 8) | i);
			}
		}
		return desc + "]";
	}

	public static void main(String[] args) {
		WhichChars test = new WhichChars("Testing 1 2 3 い あ う");
		System.out.println(test);
	}

}
