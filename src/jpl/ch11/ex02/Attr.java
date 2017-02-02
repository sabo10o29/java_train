package jpl.ch11.ex02;

/**
 * 課題11-3解答
 * Attrクラスを拡張した際に、型を限定できるため、良い考えだと考えられる
 * Attributeクラスについて
 * 
 * 
 * @author YoshikazuMurase
 *
 * @param <E>
 */

public class Attr<E> {

	private final String name;				//属性名
	private E value = null;					//保持する値 element型で定義
	
	public Attr(String name, E value){
		this.name = name;
		this.value = value;
	}
	
	//ゲッターとセッター（セッターはvalueのみ）
	public String getName(){
		return name;
	}
	
	public E getValue(){
		return this.value;
	}
	
	public E setValue(E newValue){
		E oldVal = this.value;
		this.value = newValue;
		return oldVal;
	}
	
	public String toString(){
		return name + "='" + value + "'";
	}
	
}
