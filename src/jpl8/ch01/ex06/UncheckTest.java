package jpl8.ch01.ex06;


public class UncheckTest {

	// 例外をキャッチしてキャッチされない例外に変換するメソッド
	public static Runnable uncheck(RunnableEx runnner) {
		return () -> {
			try {
				runnner.run();
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	}

	public static void main(String[] args) {
		
		//例外を処理する必要がある
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("zzz");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		
		//例外を処理する必要がない
		new Thread(uncheck(() -> {
			System.out.println("zzz");
			Thread.sleep(1000);
		})).start();

	}

}
