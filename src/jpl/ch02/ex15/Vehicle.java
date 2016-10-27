package jpl.ch02.ex15;

public class Vehicle {
	public static int nextID = 1; 
	public static String firstOwner;
	
	private double angle;
	private String owner;
	private int ID;
	private int speed;
	
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
	public int getSpeed(){
		return this.speed;
	}
	public void changeSpeed(int _speed){
		this.speed = _speed;
	}
	public void stop(){
		this.speed = 0;
	}
	
}
