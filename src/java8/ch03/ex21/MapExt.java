package java8.ch03.ex21;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Function;

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

	public static void main(String[] args) {

		// タイムスタンプを取得して取り扱うFutureクラスから、Stringを扱うFutureへ変換を行うメソッド
		// 初期化
		ExecutorService service = Executors.newCachedThreadPool();
		System.out.println("task start");
		Future<Timestamp> future = service.submit(() -> {
			Timestamp now = new Timestamp(Calendar.getInstance().getTimeInMillis());
			System.out.println("task executed!");
			return now;
		});
		Future<String> future2 = MapExt.map(future, (t) -> "実行時刻：　" + t.toString());

		try {
			System.out.println(future2.get());
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		System.out.println("task end");

		service.shutdown();

	}

}
