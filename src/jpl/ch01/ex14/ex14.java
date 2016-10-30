package jpl.ch01.ex14;

/***
 * ウォークマンを実現クラスを作成するプログラム
 * Walkmanクラスにはウォークマンのステータスを返すメソッドが必要
 * 
 */

import java.util.ArrayList;

public class ex14 {

	static final int MAX = 10;

	public static void main(String[] args) {
		ArrayList<Walkman> walkman = new ArrayList<Walkman>();
		
		for(int i = 0;i<MAX;i++){
			walkman.add(new Walkman1(Factory.getNextSirial(),"Ver. 1"));
		}
		for(int i = 0;i<MAX;i++){
			walkman.add(new Walkman2(Factory.getNextSirial(),"Ver. 2"));
		}
		for(int i = 0;i<MAX;i++){
			walkman.add(new Walkman3(Factory.getNextSirial(),"Ver. 3"));
		}
		for(int i = 0; i<walkman.size();i++){
			walkman.get(i).printSpec();
		}



	}

}
