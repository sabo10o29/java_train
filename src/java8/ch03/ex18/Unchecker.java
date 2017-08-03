package java8.ch03.ex18;

import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Unchecker {

	public static <T> Supplier<T> unchecked(Callable<T> f) {
		return () -> {
			try {
				return f.call();
			} catch (Exception e) {
				throw new RuntimeException(e);
			} catch (Throwable e) {
				throw e;
			}
		};
	}

	public static <T, U> Function<T, U> unchecked(ThrowableFunction<T, U> f, BiFunction<Exception, T, U> excConverter) {
		return (t) -> {
			try {
				return f.apply(t);
			} catch (Exception e) {
				return excConverter.apply(e, t);
			}
		};

	}

	public static void main(String[] args) {

		// 正常系の場合は結果を返し、例外が発生した場合には元の文字列を返す。
		Function<String, String> test = Unchecker.unchecked((str) -> str.substring(3, 6),
				(e, str) -> e.getMessage() + ":: " + str);
		System.out.println(test.apply("123456"));
		System.out.println(test.apply("12"));

	}

}
