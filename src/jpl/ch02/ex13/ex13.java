package jpl.ch02.ex13;

import jpl.ch02.ex13.Vehicle;

public class ex13 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle car = new Vehicle(30, "Yoshikazu Murase");
		
		System.out.println("ID: " + car.getID());
		System.out.println("Owner: " + car.getOwner());
		System.out.println("Angle: " + car.getAngle());
		
		car.setAngle(90);
		
		System.out.println("ID: " + car.getID());
		System.out.println("Owner: " + car.getOwner());
		System.out.println("Angle: " + car.getAngle());
	}

}
