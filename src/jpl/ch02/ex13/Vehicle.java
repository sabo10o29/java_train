package jpl.ch02.ex13;

public class Vehicle {
	public static int nextID = 1; 
	public static String firstOwner;
	
	private double angle;
	private String owner;
	private int speed;
	private int ID;
	
	Vehicle(double _angle, String _owner, int _speed){
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
	
	public double getAngle(){
		return this.angle;
	}
	public void setAngle(double _angle){
		this.angle = _angle;
	}
	
	public String getOwner(){
		return this.owner;
	}
	public int getID(){
		return this.ID;
	}
	
	public double getSpeed(){
		return this.speed;
	}
	public void setSpeed(int _speed){
		this.speed = _speed;
	}
}
