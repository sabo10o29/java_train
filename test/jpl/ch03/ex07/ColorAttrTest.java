package jpl.ch03.ex07;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class ColorAttrTest {

	@Test
	public void testHashCode() {
		ColorAttr testattr = new ColorAttr("test");
		assertEquals(testattr.hashCode(),testattr.getColor().hashCode());
	}

	@Test
	public void testSetValueObject() {
		Color color1 = Color.red;
		Color color2 = Color.blue;
		ColorAttr testattr = new ColorAttr("test",color1);
		Object test = testattr.setValue(color2);
		assertEquals(test,color1);
	}

	@Test
	public void testColorAttrStringObject() {
		ColorAttr testattr = new ColorAttr("test",Color.red);
		assertEquals(testattr.getColor().toString(),"java.awt.Color[r=255,g=0,b=0]");
	}

	@Test
	public void testColorAttrString() {
		ColorAttr testattr = new ColorAttr("test");
		assertEquals(testattr.getColor().toString(),"transparent");
	}

	@Test
	public void testColorAttrStringScreenColor() {
		ScreenColor test = new ScreenColor(Color.red);
		ColorAttr testattr = new ColorAttr("test",test);
		assertNotNull(testattr);
		
	}

	@Test
	public void testSetValueScreenColor() {
		ScreenColor test = new ScreenColor(Color.red);
		ColorAttr testattr = new ColorAttr("test",test);
		
		ScreenColor test2 = new ScreenColor(Color.black);
		ScreenColor old = testattr.setValue(test2);
		
		assertEquals(old,test);						//戻り値と最初に入力した値が一致するか
		assertEquals(testattr.getColor(),test2);	//引数と新しく設定した値が一致するかテスト
	}

	@Test
	public void testGetColor() {
		ScreenColor test = new ScreenColor(Color.red);
		ColorAttr testattr = new ColorAttr("test",test);
		assertEquals(testattr.getColor(),test);
	}

	@Test
	public void testDecodeColor() {
		//ColorAttrのコンストラクタにColorクラスのインスタンを代入し、保持するScreenColorオブジェクトのColorクラスが入力した値と一位するかチェック
		Color color = Color.red;
		ColorAttr test = new ColorAttr("test",color);
		test.decodeColor();
		assertEquals(test.getColor().toString(),"java.awt.Color[r=255,g=0,b=0]");
	}

	@Test
	public void testEqualsObject() {
		ColorAttr test = new ColorAttr("test",Color.red);
		assertFalse(test.equals(null));
		
	}

}
