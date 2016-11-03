package jpl.ch01.ex15;

/**
 * nameと関連した値を返すプログラム
 * テストプログラム
 * @author murase
 *
 */

public class SearchName {
	static final int MAX_SIZE = 10;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SimpleLookup lookup = new SimpleLookup(MAX_SIZE);
		
		for(Integer i = 0 ;i<12;i++){
			lookup.add(i.toString());
		}
		lookup.print();
		lookup.remove("4");
		lookup.print();
		for(Integer i = 0 ;i<12;i++){
			lookup.remove(i.toString());
		}
		
	}

}
