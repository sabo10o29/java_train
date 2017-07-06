package jpl8.ch02.ex04;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Arraysクラスのstreamメソッドを使用することでintのストリームを得ることができる。
 * @author YoshikazuMurase
 *
 */
public class IntStream {

	public static void main(String[] args) {
		int[] values = { 1, 4, 9, 16 };
		//参照型のパイプラインになる
		System.out.println(Stream.of(values));
		System.out.println(Stream.of(values).count());
		//int型のパイプラインになる
		System.out.println(Arrays.stream(values));
		System.out.println(Arrays.stream(values).count());
		
	}

}
