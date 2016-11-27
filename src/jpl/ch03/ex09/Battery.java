package jpl.ch03.ex09;

public class Battery extends EnergySource implements Cloneable{
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
	
	//クローンメソッドの実装（Vehicleクラスで複製を行うために必要）
	public Battery clone(){
		Battery obj = null;
		try {
			obj = (Battery)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	//タンクの補充
	public void chargeBattery(int charge){
		this.battery += charge;
	}
	
	//ガスの消費
	public void consumeBattery(int consumption){
		this.battery -= consumption;
	}
	
	public String toString(){
		return String.valueOf(this.battery);
	}
	
}
