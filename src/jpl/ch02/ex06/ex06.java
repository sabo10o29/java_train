package jpl.ch02.ex06;

/***
 * 生成したVeicleクラスのインスタンスをLinkedListクラスを用いてリスト化する。
 * @author murase
 *
 */

public class ex06 {
	public static void main(String[] args) {
		
		//格納する乗り物リスト
		Vehicle[] cars = {
				new Vehicle(2,"matsuda",100),
				new Vehicle(3,"toyota",122),
				new Vehicle(20,"honda",212),
				new Vehicle(20,"kawasaki",400),
				new Vehicle(20,"suzuki",111),
				new Vehicle(20,"nissan",10),
				new Vehicle(20,"yamaha",29)
		};
		
		LinkedList last = new LinkedList(cars[0]);
		LinkedList before;
	
		LinkedList list = new LinkedList(cars[1], last);
		for(int i=2; i<cars.length; i++){
			before = list;
			list = new LinkedList(cars[i], before);
		}
		list.DispLink();
	}

}
