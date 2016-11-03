package jpl.ch02.ex11;

import jpl.ch02.ex11.LinkedList;
import jpl.ch02.ex11.Vehicle;

/***
 * LinkedListクラスにtoStringメソッドを追加
 * @author murase
 *
 */

public class ex11 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//格納する乗り物リスト
		Vehicle[] cars = {
				new Vehicle(2,"matsuda",100),
				new Vehicle(3,"toyota",200),
				new Vehicle(20,"honda",200),
				new Vehicle(20,"kawasaki",300),
				new Vehicle(20,"suzuki",400),
				new Vehicle(20,"nissan",223),
				new Vehicle(20,"yamaha",342),
				new Vehicle()
		};
		
		//
		LinkedList last = new LinkedList(cars[0]);
		LinkedList before;
	
		LinkedList list = new LinkedList(cars[1], last);
		for(int i=2; i<cars.length; i++){
			before = list;
			list = new LinkedList(cars[i], before);
		}
		//list.DispLink();
		System.out.println(list.toString());
	}

}
