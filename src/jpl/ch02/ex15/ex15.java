package jpl.ch02.ex15;

public class ex15 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle car = new Vehicle(30, "Yoshikazu Murase");
		System.out.println("Speed: "+car.getSpeed());
		car.changeSpeed(100);
		System.out.println("Speed: "+car.getSpeed());
		car.stop();
		System.out.println("Speed: "+car.getSpeed());
		
		
	}

}
