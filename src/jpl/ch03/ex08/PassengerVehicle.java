package jpl.ch03.ex08;

/**
 * 車の座席数と座っている人数を拡張したPassengerVehicleクラスを作成する。
 * クラス内にメインメソッドを作成し、PassengerVehicleオブジェクトを数個作成する
 * @author murase
 *
 */


public class PassengerVehicle extends Vehicle implements Cloneable{
	private int seats;
	private int numPassengers;	
	
	//仮テスト：複製後に元の保持しているオブジェクトを操作してもクローンに反映されないことを確認する
	public static void main(String[] args) {
		//Vehicleクラスのテスト
		Vehicle car1 = new Vehicle("Tanaka");
		car1.changeSpeed(100);
		car1.chargeBattery(120);
		car1.fillGastank(1000);
		Vehicle clone_car1 = car1.clone();
		car1.consumeBattery(30);
		car1.consumeGastank(500);
		System.out.println("Vehicleクラスのテスト");
		System.out.println("元のGarageインスタンス");
		System.out.println(car1.toString());
		System.out.println("複製されたGarageインスタンス");
		System.out.println(clone_car1.toString());
		
		System.out.println("\n\n");
		
		//PassengerVehicleのテスト
		PassengerVehicle car2 = new PassengerVehicle("Murase", 2);
		car2.chargeBattery(1234);
		car2.fillGastank(5678);
		car2.ridePassenger();
		PassengerVehicle clone_car2 = car2.clone();
		car2.dropPassenger();
		car2.consumeBattery(234);
		car2.consumeGastank(678);
		System.out.println("Vehicleクラスのテスト");
		System.out.println("元のGarageインスタンス");
		System.out.println(car2.toString());
		System.out.println("複製されたGarageインスタンス");
		System.out.println(clone_car2.toString());
	}
	
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
