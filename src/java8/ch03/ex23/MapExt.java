package java8.ch03.ex23;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

import javafx.util.Pair;

public class MapExt {

	public static <T, U> List<U> map(List<T> list, Function<T, U> func) {

		List<U> result = new ArrayList<>();
		for (T t : list) {
			result.add(func.apply(t));
		}
		return result;

	}

	public static <T, U> Future<U> map(Future<T> future, Function<T, U> func) {

		return new Future<U>() {

			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				return future.cancel(mayInterruptIfRunning);
			}

			@Override
			public boolean isCancelled() {
				return future.isCancelled();
			}

			@Override
			public boolean isDone() {
				return future.isDone();
			}

			@Override
			public U get() throws InterruptedException, ExecutionException {
				return func.apply(future.get());
			}

			@Override
			public U get(long timeout, TimeUnit unit)
					throws InterruptedException, ExecutionException, TimeoutException {
				return func.apply(future.get(timeout, unit));
			}
		};

	}

	public static <T, U> Pair<U, U> map(Pair<T, T> p, Function<T, U> func) {
		return new Pair<>(func.apply(p.getKey()), func.apply(p.getValue()));
	}

	public static void main(String[] args) {

		// 国名-国際電話番号のペアから国番号-国際電話番号のペアへ変換を行う
		Pair<String, String> p1 = new Pair<String, String>("Japan", "81");
		Pair<Integer, Integer> p2 = MapExt.map(p1, (str) -> {
			if ("Japan" == str) {
				return 392;
			} else {
				return Integer.parseInt(str);
			}
		});

		System.out.println(p1.getKey() + ": " + p1.getValue());
		System.out.println(p2.getKey() + ": " + p2.getValue());

	}

}
