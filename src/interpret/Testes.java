package interpret;

import java.awt.Color;
import java.awt.Window;
import java.awt.color.ColorSpace;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class Testes {
	
	public Testes(Color color){
		
	}

	public static void main(String[] args) {
		String str = "javax.swing.JComponent";
		Class<?> clazz = null;
		try {
			clazz = Class.forName(str);
		} catch (ClassNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
		
		int mod = clazz.getModifiers();
		
		if(Modifier.isAbstract(mod)){
			System.out.println("抽象クラス");
		}
		if(Modifier.isInterface(mod)){
			System.out.println("インターフェース");
		}
		if(clazz.isAnonymousClass()){
			System.out.println("無名クラス");
		}
		if(clazz.isPrimitive()){
			System.out.println("プリミティブ型");
		}
		

	}

}
