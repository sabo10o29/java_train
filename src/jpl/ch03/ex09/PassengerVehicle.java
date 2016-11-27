package jpl.ch03.ex09;

/**
 * 車の座席数と座っている人数を拡張したPassengerVehicleクラスを作成する。
 * クラス内にメインメソッドを作成し、PassengerVehicleオブジェクトを数個作成する
 * @author murase
 *
 */


public class PassengerVehicle extends Vehicle implements Cloneable{
	private int seats;
	private int numPassengers;	
	
	//コンストラクタ
	PassengerVehicle(String _owner, int _seats){
		super(_owner);
		this.seats = _seats;
	}
	
	//クローンメソッドの実装
	public PassengerVehicle clone(){
		PassengerVehicle obj = null;
		obj = (PassengerVehicle)super.clone();
		return obj;
	}
	
	//ゲッターセッター
	public int seats(){
		return this.seats;
	}
	public int numPassenger(){
		return this.numPassengers;
	}
	private void ridePassenger(){
		this.numPassengers++;
	}
	private void dropPassenger(){
		this.numPassengers--;
	}
	public boolean setPassengers(int _passengers){
		if(this.seats()<_passengers){
			return false;
		}else{
			this.numPassengers = _passengers;
			return true;
		}
	}
	
	public String toString(){
		String str = 
				super.toString() + "\n"
				+ "Seats: " + Integer.toString(this.seats) + ", Passenger: " + this.numPassengers;
		return str;
	}
	
	
}
