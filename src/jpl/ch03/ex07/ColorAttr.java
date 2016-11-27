package jpl.ch03.ex07;

public class ColorAttr extends Attr{
	private ScreenColor myColor;
	
	public ColorAttr(String name, Object value){
		super(name,value);
		decodeColor();
	}
	//値が存在しない場合にはString型で透明色を設定
	public ColorAttr(String name){
		this(name,"transparent");
	}
	
	//背景色型を用いた初期化
	public ColorAttr(String name, ScreenColor value){
		super(name, value.toString());
		myColor = value;
	}
	
	//オーバーライド
	public Object setValue(Object newValue){
		//スーパークラスのsetValueを最初に行う
		Object retval = super.setValue(newValue);
		decodeColor();							//newValueをScreenColor型に変換し、myColorを更新する
		return retval;							//古い値を返す
	}
	
	//値を記述ではなくScreenColorに設定する
	public ScreenColor setValue(ScreenColor newValue){
		//スーパークラスのsetValueを最初に行う
		super.setValue(newValue.toString());	//ScreenColorのtoString名に値を設定
		ScreenColor oldValue = myColor;			
		myColor = newValue;						//新しい値に設定
		return oldValue;						//古い値を返す
	}
	
	//変換されたScreenColorオブジェクトを返す
	public ScreenColor getColor(){
		return myColor;
	}
	
	//getValue()で得られる記述からScreenColorを設定する
	//オブジェクト型からScreenColor型に変換を行い、myColorを更新する
	protected void decodeColor(){
		if(getValue() == null){
			myColor = null;
		}else{
			myColor = new ScreenColor(getValue());
		}
	}
	
	//ex07：equalsのオーバーライド
	public boolean equals(Object obj){
		return this.myColor.equals(obj);
	}
	
	//ex07：hashCodeのオーバーライド
	public int hashCode(){
		return this.myColor.hashCode();
	}
	
}
