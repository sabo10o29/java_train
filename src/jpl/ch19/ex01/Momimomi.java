package jpl.ch19.ex01;

import jpl.ch19.ex01.LinkedList;

public class Momimomi {

	public static void main(String[] args) {
		LinkedList ll = null;
		// リストの作成
		for (int i = 0; i < 5; i++) {
			LinkedList next = ll;
			if (i == 0) {
				ll = new LinkedList(i);
			} else {
				ll = new LinkedList(i, next);
			}
		}

		// 表示
		System.out.println("リストは全部で" + ll.getNumList());
		System.out.println(ll.toString());

	}

}
