package jpl.ch02.ex06;

public class Vehicle {
	public static int nextID = 1; 
	
	private double angle;
	private String owner;
	private int ID;
	
	Vehicle(double _angle, String _owner){
		this.angle = _angle;
		this.owner = _owner;
		this.ID = this.nextID++;
	}
	
	public void showInfo(){
		System.out.println("Owner: "+this.owner);
		System.out.println("ID: "+this.ID);
	}
}
