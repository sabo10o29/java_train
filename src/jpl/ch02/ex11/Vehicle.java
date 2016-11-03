package jpl.ch02.ex11;

public class Vehicle {
	public static int nextID = 1; 
	public static String firstOwner;
	
	private double angle;
	private String owner;
	private int speed;
	private int ID;
	
	Vehicle(double _angle, String _owner,int _speed){
		this.firstOwner = _owner;
		this.angle = _angle;
		this.owner = _owner;
		this.speed = _speed;
		this.ID = this.nextID++;
	}
	Vehicle(){
		this.owner = this.firstOwner;
		this.ID = this.nextID++;
	}
	
	public void showInfo(){
		System.out.println("Owner: "+this.owner);
		System.out.println("ID: "+this.ID);
		System.out.println("Angle: " + this.angle + ", Speed: " + this.speed);
	}
	
	public static int maxID(){
		return nextID-1;
	}
	
	public String toString(){
		String str = this.ID + "(" + this.owner + ")";
		return str;
	}
}
