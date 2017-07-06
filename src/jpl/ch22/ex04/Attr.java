package jpl.ch22.ex04;


public class Attr {

	private final String name;					//属性名
	private Object value = null;				//保持する値
	
	public Attr(String name, Object value){
		this.name = name;
		this.value = value;
	}
	
	//ゲッターとセッター（セッターはvalueのみ）
	public String getName(){
		return name;
	}
	
	public Object getValue(){
		return value;
	}
	
	public Object setValue(Object newValue){
		Object oldVal = value;
		value = newValue;
		return oldVal;
	}
	
	public String toString(){
		return name + "='" + value + "'";
	}
	
}
