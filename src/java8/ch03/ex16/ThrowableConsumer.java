package java8.ch03.ex16;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ThrowableConsumer {

	public static <T> void doInOrderAsync(Supplier<T> first, BiConsumer<T, Throwable> second) {

		Thread t = new Thread() {
			public void run() {
				T result = null;
				try {
					result = first.get();
					second.accept(result, null);
				} catch (Throwable e) {
					second.accept(result, e);
				}
			}
		};
		t.start();

	}

	public static void main(String[] args) {

		// 正常系、異常系、finally節の処理をまとめて記述できる。
		Supplier<String> first = () -> {
			return "成功！";
		};
		Supplier<String> first2 = () -> {
			int[] a = new int[1];
			a[2] = 4;
			return "成功！";
		};

		BiConsumer<String, Throwable> seconod = (result, e) -> {
			System.out.print("結果：　");
			if (e != null) {
				System.out.println("失敗");
				e.printStackTrace();
			} else {
				System.out.println(result);
			}
		};

		ThrowableConsumer.doInOrderAsync(first, seconod);
		ThrowableConsumer.doInOrderAsync(first2, seconod);
	}

}
