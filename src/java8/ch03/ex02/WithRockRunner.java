package java8.ch03.ex02;

import java.util.concurrent.locks.ReentrantLock;

import com.sun.org.apache.bcel.internal.generic.LoadClass;

/**
 * 処理を行いたい内容をルんあbれインタフェースで取得し、ロックを取得した際に実行する
 * 
 * @author YoshikazuMurase
 *
 */
public class WithRockRunner {

	public static void withRock(ReentrantLock lock, Runnable runner) {

		lock.lock();
		try {
			runner.run();
		} finally {
			lock.unlock();
		}

	}

	public static void main(String[] args) {
		ReentrantLock lock = new ReentrantLock();

		// withRockメソッドを複数実行するスレッドを生成
		// スレッドを実行
		//　ロックを獲得するまで待たされる
		for (int i = 0; i < 5; i++) {
			String str = i + "番目のスレッドです。";

			new Thread(() -> {

				int count = 0;
				while (count < 10) {
					WithRockRunner.withRock(lock, () -> {
						System.out.println(str);
					});
					count++;
				}

			}).start();
		}

	}

}
