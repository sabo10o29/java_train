package jpl8.ch01.ex07;

public class Runnner {

	public static void main(String[] args) {

		new Thread(andThen(() -> {
			System.out.println("TestA");
		}, () -> {
			System.out.println("TestB");
		})).start();

	}

	public static Runnable andThen(Runnable a, Runnable b) {
		return () -> {
			a.run();
			b.run();
		};

	}

}
