package java8.ch02.ex05;

import java.util.stream.Stream;

public class LCG {

	public static void main(String[] args) {

		Stream<Long> st = LCG.getLCG(3141592);
		st.limit(100).forEach(System.out::println);

	}

	public static Stream<Long> getLCG(long seed) {
		long x0 = seed;
		long a = 25214903917L;
		long c = 11;
		long m = 2 ^ 48;

		return Stream.iterate(x0, x -> (a * x + c) % m);

	}

}
