package jpl.ch03.ex08;

public class GasTank extends EnergySource implements Cloneable{
	private int gastank;
	
	//コンストラクタ
	GasTank(){
	}
	
	//空かどうかチェックするメソッド
	public boolean empty(){
		if(this.gastank != 0){
			return true;
		}else{
			return false;
		}
	}
	
	//クローンメソッドの実装（Vehicleクラスで複製を行うために必要）
	public GasTank clone(){
		GasTank obj = null;
		try {
			obj = (GasTank)super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	//タンクの補充
	public void fillGas(int gas){
		this.gastank += gas;
	}
	
	//ガスの消費
	public void consumeGas(int consumption){
		this.gastank -= consumption;
	}
	
	public String toString(){
		return String.valueOf(this.gastank);
	}

}
