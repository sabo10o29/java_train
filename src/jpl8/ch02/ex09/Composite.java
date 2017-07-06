package jpl8.ch02.ex09;

import java.util.ArrayList;
import java.util.stream.Stream;


public class Composite {

	public static <T> ArrayList<T> composite(Stream<ArrayList<T>> stream) {

		ArrayList<T> test = stream.reduce(new ArrayList<T>(), (x, y) -> {
			if (x.addAll(y))
				return x;
			return x;
		}, (x, y) -> {
			if (x.addAll(y))
				return x;
			return x;
		});

		return test;
	}

	public static void main(String[] args) {

		ArrayList<String>[] test = new ArrayList[10];
		for (int j = 0; j < 10; j++) {
			test[j] = new ArrayList<String>();
			for (int i = 0; i < 20; i++) {
				test[j].add(String.valueOf(10 * j + i));
			}
		}
		Stream<ArrayList<String>> stream = Stream.of(test);
		ArrayList<String> result = Composite.composite(stream);

		result.stream().forEach(System.out::println);

	}

}
