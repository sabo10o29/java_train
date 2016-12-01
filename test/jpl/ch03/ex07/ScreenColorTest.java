package jpl.ch03.ex07;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class ScreenColorTest {
	
	//コンストラクタのテスト（引数なし）
	@Test
	public void testScreenColor() {
		ScreenColor test = new ScreenColor();
		assertNull(test.toString());
	}

	@Test
	public void testScreenColorObject() {
		Object obj = new Object();
		ScreenColor test = new ScreenColor(obj);
		assertNotNull(test.toString());
	}

	@Test
	public void testScreenColorColor() {
		Color color = Color.red;
		ScreenColor test = new ScreenColor(color);
		assertNotNull(test.toString());
	}

}
