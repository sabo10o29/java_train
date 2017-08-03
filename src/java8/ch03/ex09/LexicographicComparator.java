package java8.ch03.ex09;

import java.awt.Color;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

public class LexicographicComparator {

	/**
	 * 引数のフィールドを順々に比較を行うメソッド 値が比較できる場合：結果を返す 値が比較できない場合：次のフィールドの比較を行う
	 * すべてのフィールドが一致していた場合：０を返す フィールドを取得できない場合には例外を返す
	 * 
	 * @param strings
	 * @return
	 */
	public static <T> Comparator<T> lexicographicComparator(String... strings) {
		return (a, b) -> {

			for (String fname : strings) {
				Object aVal = null;
				Object bVal = null;
				try {
					aVal = getValue(a, fname);
					bVal = getValue(b, fname);
				} catch (Exception e) {
					continue;	//フィールド値の取得で例外が発生した場合にはスキップ
				}
				if (!aVal.equals(bVal)) { // 値が等しくない場合には以下の処理を実行
					if (aVal instanceof Comparable) {
						return ((Comparable) aVal).compareTo(bVal);
					}
				}
			}
			// すべてのフィールドが一致(例外が発生したフィールドは無視)	
			return 0;

		};
	}

	// インスタンスから対象の値を取得するメソッド
	public static Object getValue(Object instance, String fname) throws Exception {
		try {
			Field field = instance.getClass().getDeclaredField(fname);
			return field.get(instance);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new Exception(e);
		}
	}

	public static void main(String[] args) {

		class Person {
			public int age;
			public String name;
			public Color favoColor;

			public Person(int age, String name, Color c) {
				this.age = age;
				this.name = name;
				this.favoColor = c;
			}

			public String toString() {
				return this.name;
			}

		}

		Person[] students = new Person[6];
		students[0] = new Person(25, "kawasaki", Color.GREEN);
		students[1] = new Person(25, "honda", Color.BLUE);
		students[2] = new Person(25, "suzuki", Color.YELLOW);
		students[3] = new Person(25, "yamaha", Color.YELLOW);
		students[4] = new Person(16, "bmw", Color.BLUE);
		students[5] = new Person(16, "bmw", Color.BLUE);

		Arrays.sort(students, lexicographicComparator("age", "hoge", "favoColor", "name"));
		Arrays.stream(students).forEach(System.out::println);

	}

}
