package jpl.ch02.ex09;

public class Vehicle {
	public static int nextID = 1; 
	public static String firstOwner;
	
	private double angle;
	private String owner;
	private int ID;
	
	Vehicle(double _angle, String _owner){
		this.firstOwner = _owner;
		this.angle = _angle;
		this.owner = _owner;
		this.ID = this.nextID++;
	}
	Vehicle(){
		this.owner = this.firstOwner;
		this.ID = this.nextID++;
	}
	
	public void showInfo(){
		System.out.println("Owner: "+this.owner);
		System.out.println("ID: "+this.ID);
	}
	
	public static int maxID(){
		return nextID-1;
	}
	
}
