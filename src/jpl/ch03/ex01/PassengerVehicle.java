package jpl.ch03.ex01;

/**
 * 車の座席数と座っている人数を拡張したPassengerVehicleクラスを作成する。
 * クラス内にメインメソッドを作成し、PassengerVehicleオブジェクトを数個作成する
 * @author murase
 *
 */


public class PassengerVehicle extends Vehicle{
	private int seats;
	private int numPassengers;	
	
	public static void main(String[] args) {
		//クラスの作成
		PassengerVehicle[] car = {
			new PassengerVehicle("Murase", 2),
			new PassengerVehicle("Taro", 8),
			new PassengerVehicle("Tanaka",4)
		};
		//オブジェクト乗客を追加
		car[0].setPassengers(4);
		car[1].setPassengers(7);
		car[2].setPassengers(3);
		//オブジェクトの表示
		for(int i= 0;i<3;i++){
			System.out.println(car[i].toString()); 
		}
	}
	
	PassengerVehicle(String _owner, int _seats){
		super(_owner);
		this.seats = _seats;
	}
	
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
		String str = "Seats: " + Integer.toString(this.seats) + ", Passenger: " + this.numPassengers;
		return str;
	}
	
	
}
