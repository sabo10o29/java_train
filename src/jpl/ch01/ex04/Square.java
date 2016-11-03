package jpl.ch01.ex04;

/**
 * 二乗を表示するプログラム
 * @author murase
 */

public class Square {

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ
		System.out.println("１～10の二乗を生成するプログラム");
		for(int i=1;i<11;i++){

			System.out.println(i+"の２乗"+Math.pow(i, 2));
		}
	}

}
