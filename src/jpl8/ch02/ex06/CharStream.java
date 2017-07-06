package jpl8.ch02.ex06;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class CharStream {

	public static Stream<Character> characterStream(String s) {

		return IntStream.range(0, s.length()).boxed().map(s::charAt);

	}

	public static Stream<Character> characterStream_old(String s) {

		List<Character> result = new ArrayList<>();
		for (char c : s.toCharArray())
			result.add(c);
		return result.stream();

	}

	public static void main(String[] args) {
		String s = "Hello";
		Stream<Character> st = characterStream(s);
		st.forEach(System.out::println);

	}

}
