package java8.ch02.ex08;

import java.util.Arrays;
import java.util.stream.Stream;

import java8.ch02.ex06.CharStream;

public class zip {

	public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {

		Stream<T> result = Stream.empty();
		// Stream<T> f = first.peek(e -> System.out.println(e)).skip(i);
		// Stream<T> s = second.peek(e -> System.out.println(e)).skip(i);
		Stream<T> f = first.skip(0);
		Stream<T> s = second.skip(0);

		for (int i = 0; i < 100; i++) {
			f = f.skip(1);
			s = s.skip(1);
			result = Stream.concat(result, f);
			result = Stream.concat(result, s);
		}

		return result;
	}

	public static void main(String[] args) {

		Stream<Character> test = zip.zip(CharStream.characterStream("hogehoge"), CharStream.characterStream("testes"));
		test.forEach(System.out::println);

	}

}
