package jpl.ch04.ex01;

public class Battery implements EnergySource{
	private int battery;
	
	//コンストラクタ
	Battery(){
	}
	
	//空かどうかチェックするメソッド
	public boolean empty(){
		if(this.battery != 0){
			return true;
		}else{
			return false;
		}
	}
	
	//タンクの補充
	public void chargeBattery(int charge){
		this.battery += charge;
	}
	
	//ガスの消費
	public void consumeBattery(int consumption){
		this.battery -= consumption;
	}
	
}
