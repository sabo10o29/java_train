package jpl8.ch01.ex01;

import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * Arrays.sortで呼び出されるメソッドはメソッドを呼び出したスレッドで実行される
 * 
 * @author YoshikazuMurase
 *
 */
public class ThreadCheck {

	public static void main(String[] args) {

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {

				Double[] ramdoms = Stream.generate(Math::random).limit(20).peek(System.out::println)
						.toArray(i -> new Double[i]);

				Comparator<Double> comp = (first, second) -> {
					System.out.println(Thread.currentThread().getName());
					return Double.compare(first, second);
				};

				Arrays.sort(ramdoms, comp);
				Stream.of(ramdoms).forEach(System.out::println);

			}
		}, "Sub");

		t.start();

	}

}
