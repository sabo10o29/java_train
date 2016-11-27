package jpl.ch03.ex06;

public class GasTank extends EnergySource{
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
	
	//タンクの補充
	public void fillGas(int gas){
		this.gastank += gas;
	}
	
	//ガスの消費
	public void consumeGas(int consumption){
		this.gastank -= consumption;
	}

}
