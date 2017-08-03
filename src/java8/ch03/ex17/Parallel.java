package java8.ch03.ex17;

import java.util.function.Consumer;

public class Parallel {

	public static void doInParallelAsync(Runnable first, Runnable second, Consumer<Throwable> handler) {

		Runnable f = () -> {
			try {
				first.run();
			} catch (Throwable e) {
				synchronized (handler) {
					handler.accept(e);
				}
			}
		};
		Runnable s = () -> {
			try {
				second.run();
			} catch (Throwable e) {
				synchronized (handler) {
					handler.accept(e);
				}
			}
		};

		new Thread(f).start();
		new Thread(s).start();

	}

	public static void main(String[] args) {

		int[] a = new int[1000];
		int[] b = new int[500];

		Runnable test = () -> {
			int i = 0;
			while (true) {
				System.out.println("test1: " + i);
				a[i] = 1;
				i++;
			}
		};
		Runnable test2 = () -> {
			int i = 0;
			while (true) {
				System.out.println("test2: " + i);
				b[i] = 1;
				i++;
			}
		};
		Consumer<Throwable> handler = (e) -> {
			System.out.println();
			System.out.println(e.getMessage());
			e.printStackTrace();
		};

		Parallel.doInParallelAsync(test, test2, handler);

	}

}
