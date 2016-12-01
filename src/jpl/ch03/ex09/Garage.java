package jpl.ch03.ex09;

public class Garage implements Cloneable{
    public static final int MAX = 100;
	public Vehicle[] arrayVehicle = new Vehicle[MAX];
	private int numVehicle=0;
	
	public static void main(String[] args) {
		Garage garage = new Garage();					//複製元インスタンス
		Garage clone_garage;							//複製インスタンス
		//複製元の初期化
		garage.loading(new Vehicle("Murase"));
		garage.loading(new Vehicle("Tanaka"));
		garage.arrayVehicle[0].chargeBattery(100);
		garage.arrayVehicle[0].fillGastank(500);
		garage.arrayVehicle[0].changeSpeed(340);
		//現時点の状態を複製
		clone_garage = garage.clone();
		//複製元の情報を変更
		garage.unloading();
		garage.arrayVehicle[0].consumeBattery(20);
		garage.arrayVehicle[0].consumeGastank(100);
		garage.arrayVehicle[0].changeSpeed(111);
		//複製元と複製の情報を比較（元の変更が複製に反映されていなければ成功）
		System.out.println("元のGarageインスタンス");
		System.out.println(garage.toString());
		System.out.println("複製されたGarageインスタンス");
		System.out.println(clone_garage.toString());
		
	}
	
	//コンストラクタ
	public Garage(){
		
	}
	
	//クローンメソッドの実装
	//arrayVehicleに格納されたVehicleクラスのインスタンスも複製する必要がある
	public Garage clone(){
		Garage obj = null;
		try {
			obj = (Garage)super.clone();								//スーパークラスの複製
			obj.arrayVehicle = new Vehicle[MAX];						//重要！！：複製のarrayVehicleの参照先を変更する必要がある
			for(int i = 0; i<this.numVehicle; i++){
				obj.arrayVehicle[i] = this.arrayVehicle[i].clone();		//格納されているVehicleクラスを個々にクローンする必要がある
			}
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return obj;
	}
	
	//車の入庫
	public void loading(Vehicle _vehicle){
		if(this.numVehicle<MAX){
			this.arrayVehicle[this.numVehicle++] = _vehicle;
		}else{
			//何もしない
		}
	}
	
	//車の出庫
	public Vehicle unloading(){
		Vehicle vehicle;
		if(this.numVehicle!=0){
			 vehicle = this.arrayVehicle[this.numVehicle];
			 this.arrayVehicle[--this.numVehicle] = null;
			return vehicle;
		}else{
			return null;
		}
	}
	
	//倉庫に格納されている車の情報を表示する
	public String toString(){
		String str = "Strage vehicle list \n";
		for(int i = 0; i<this.numVehicle; i++){
			str += (this.arrayVehicle[i].toString() + "\n");
		}
		return str;
	}
	

}
