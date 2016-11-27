package jpl.ch03.ex06;

public class Vehicle {
	public static int nextID = 1; 
	public static String firstOwner;
	
	//定数
	public static final int TURN_LEFT = -90;
	public static final int TURN_RIGHT = 90;
	
	//属性値
	private double angle;						//角度
	private String owner;						//所有者
	private int speed;							//現在のスピード
	private int ID;								//車のID
	private GasTank gastank = new GasTank();	//ch03.ex06
	private Battery battery = new Battery();	//
	
	//コンストラクタ
	Vehicle(double _angle, String _owner, int _speed){
		this.firstOwner = _owner;
		this.angle = _angle;
		this.owner = _owner;
		this.speed = _speed;
		this.ID = this.nextID++;
	}
	Vehicle(String _owner){
		this.firstOwner = _owner;
		this.owner = _owner;
		this.ID = this.nextID++;
	}
	Vehicle(){
		this.owner = this.firstOwner;
		this.ID = this.nextID++;
	}
	
	//Vehicleインスタンスの情報を返すメソッド
	public void showInfo(){
		System.out.println("Owner: "+this.owner);
		System.out.println("ID: "+this.ID);
		System.out.println("Angle: " + this.angle + ", Speed: " + this.speed);
	}
	
	//IDの最大値を返すメソッド
	public static int maxID(){
		return nextID-1;
	}
	
	//toStringメソッド：オーバーライド
	public String toString(){
		String str = this.ID + "(" + this.owner + ")";
		return str;
	}
	
	//ゲッターセッター
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
	
	//変速メソッド
	public void changeSpeed(int _speed){
		this.speed = _speed;
	}
	//停止メソッド
	public void stop(){
		this.speed = 0;
	}
	
	//回転メソッド
	public void turn(double _angle){
		this.angle = _angle;
	}
	public void turn(int _value){
		this.angle += _value;
	}
	
	//動力源が空でないことを確認するメソッド
	public boolean start(){
		//ガスかバッテリーのどちらかがあればtrueを返す
		if(this.gastank.empty()||this.battery.empty()){
			return true;
		}else{
			return false;
		}
	}
	
	//バッテリー&ガソリンの補充
	public void chargeBattery(int _charge){
		this.battery.chargeBattery(_charge);
	}
	public void fillGastank(int _gas){
		this.gastank.fillGas(_gas);
	}
	//バッテリー&ガソリンの消費
	public void consumeBattery(int _consumption){
		this.battery.consumeBattery(_consumption);
	}
	public void consumeGastank(int _consumption){
		this.gastank.consumeGas(_consumption);
	}
}