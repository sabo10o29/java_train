package jpl.ch01.ex15;

public class SimpleLookup implements Lookup{
	private String[] names;
	private Object[] values;
	private int currentNum = 0;
	
	SimpleLookup(int MAX_SIZE){
		names = new String[MAX_SIZE];
		values = new Object[MAX_SIZE];
	}
	
	@Override
	public Object find(String name) {
		// TODO Auto-generated method stub
		for(int i=0; i<names.length; i++){
			if(names[i].equals(name))
				return values[i];
		}
		return null;
	}
	//オブジェクトを追加する
	public void add(String _name){
		if(currentNum!=this.names.length){
			this.names[this.currentNum] = _name;
			this.currentNum++;
		}else{
			System.out.println("Too many add object");
		}
	}
	//指定したオブジェクトを削除する
	public void remove(String name){
		if(currentNum!=0){
			for(int i=0;i<this.currentNum;i++){
				if(name.equals(this.names[i])){
					this.push(i);
					this.currentNum--;
				}
			}
		}else{
			System.out.println("No object!");
		}
	}
	//指定したオブジェクを削除して詰める
	private void push(int deleteNum){
		for(int i = deleteNum+1;i<this.names.length;i++){
			this.names[i-1] = this.names[i];
			this.values[i-1] = this.values[i];
		}
	}
	//オブジェクトの一覧を表示する
	public void print(){
		for(int i = 0; i<this.currentNum; i++){
			System.out.println(this.names[i]);
		}
	}
	

}
