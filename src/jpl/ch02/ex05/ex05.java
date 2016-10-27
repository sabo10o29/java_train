package jpl.ch02.ex05;

public class ex05 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle[] cars = {
				new Vehicle(2,"taro"),
				new Vehicle(3,"tanaka"),
				new Vehicle(20,"yamada")
		};
		for(int i=0; i<cars.length; i++){
			cars[i].showInfo();
		}
		
		
		
	}

}
