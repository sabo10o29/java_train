package java8.ch02.ex01;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import jpl.ch14.ex10.ThreadPool;

/**
 * カウンターを更新する為には同期処理が必要となるため
 * 
 * @author YoshikazuMurase
 *
 */
public class CountWords implements Runnable {

	public AtomicInteger count;

	private final List<String> words;

	public CountWords(List<String> words, AtomicInteger count) {
		this.words = words;
		this.count = count;
	}

	@Override
	public void run() {
		int subcount = 0;
		for (String w : words) {
			if (w.length() > 12)
				subcount++;

		}
		count.getAndAdd(subcount);
	}

	public static void main(String[] args) {

		try {
			String path = new File(".").getAbsoluteFile().getParent();
			String contents = new String(Files.readAllBytes(Paths.get(path + "/src/jpl8/ch02/ex01/WarAndPeace.txt")),
					StandardCharsets.UTF_8);

			List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

			int wordsSize = 40;
			int queueSize = (int) Math.ceil(words.size() / wordsSize);
			ThreadPool pool = new ThreadPool(100, 25);
			pool.start();

			AtomicInteger count = new AtomicInteger(0);
			for (int i = 0; i < queueSize; i++) {
				int start = i * wordsSize;
				int end = (i + 1) * wordsSize - 1;
				List<String> subwords = words.subList(start, end);
				pool.dispatch(new CountWords(subwords, count));
			}

			pool.stop();
			System.out.println("文字数が12以上の単語の数：" + count);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
