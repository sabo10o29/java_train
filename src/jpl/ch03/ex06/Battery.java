package jpl.ch03.ex06;

public class Battery extends EnergySource{
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
	
	//テスト用メソッド
	protected int battery(){
		return this.battery;
	}
	
}
