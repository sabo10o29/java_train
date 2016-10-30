package jpl.ch02.ex10;

public class Vehicle {
	public static int nextID = 1; 
	public static String firstOwner;
	
	private double angle;
	private String owner;
	private int speed;
	private int ID;
	
	//オーナー、スピード、角度を引数として用いるコンストラクタ
	Vehicle(double _angle, String _owner, int _speed){
		this.firstOwner = _owner;
		this.angle = _angle;
		this.owner = _owner;
		this.speed = _speed;
		this.ID = this.nextID++;
	}
	//最初のオーナをフィールド値として記憶しておき、引数がない場合には最初のオーナーに設定を行うコンストラクタ
	Vehicle(){
		this.owner = this.firstOwner;
		this.ID = this.nextID++;
	}
	
	public void showInfo(){
		System.out.println("Owner: "+this.owner);
		System.out.println("ID: "+this.ID);
		System.out.println("Angle: " + this.angle + ", Speed: " + this.speed);
	}
	
	public String getOwner(){
		return this.owner;
	}
	
	public static int maxID(){
		return nextID-1;
	}
		
	public String toString(){
		String str = this.ID + "(" + this.owner + ")";
		return str;
	}
		
}

	
