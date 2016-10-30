package jpl.ch02.ex03;

/***
 * 次の車体のIDを持つフィールドと車体のIDをViecleクラスに新たに追加
 * @author murase
 *
 */

public class ex03 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle test1 = new Vehicle(20.0, "taro", 40);
		Vehicle test2 = new Vehicle(20.0, "tanaka",50);
		
		test1.showInfo();
		test2.showInfo();
		
	}

}
