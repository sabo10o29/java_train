package interpret;

import java.awt.Window;
import java.awt.color.ColorSpace;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;

import javax.swing.JComboBox;
import javax.swing.JFrame;

public class Testes {

	public static void main(String[] args) {
		Class<?> clazz = int.class;
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
