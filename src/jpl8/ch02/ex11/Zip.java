package jpl8.ch02.ex11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Stream;

public class Zip {

	/**
	 * ストリームの順番とその倍数に挿入することでArrayListを作成する
	 * 
	 * @return
	 */
	public static <T> ArrayList<T> Composite(int size, Stream<ArrayList<T>> stream) {

		ArrayList<T> result = new ArrayList<>(Collections.nCopies(size, null));

		return null;
	}

	public static void main(String[] args) {
		// TODO 自動生成されたメソッド・スタブ

	}

}
