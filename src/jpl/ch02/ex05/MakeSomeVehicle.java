package jpl.ch02.ex05;

/***
 * 幾つかのインスタンスを生成して、そのフィールドを表示する
 * @author murase
 *
 */

public class MakeSomeVehicle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle[] cars = {
				new Vehicle(2,"taro",100),
				new Vehicle(3,"tanaka",200),
				new Vehicle(20,"yamada",340)
		};
		for(int i=0; i<cars.length; i++){
			cars[i].showInfo();
		}
		
		
		
	}

}
