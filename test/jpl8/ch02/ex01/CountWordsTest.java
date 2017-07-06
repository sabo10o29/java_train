package jpl8.ch02.ex01;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.Test;

import jpl.ch14.ex10.ThreadPool;

public class CountWordsTest {

	@Test
	public void testCountWords() {
		// 100回同じ文書で実施して結果がすべて同じかどうか
		String path = null;
		String contents = null;
		List<String> words = null;
		ThreadPool pool = null;
		int wordsSize = 40;
		int queueSize = 0;
		try {
			path = new File(".").getAbsoluteFile().getParent();
			contents = new String(Files.readAllBytes(Paths.get(path + "/src/jpl8/ch02/ex01/WarAndPeace.txt")),
					StandardCharsets.UTF_8);

			words = Arrays.asList(contents.split("[\\P{L}]+"));

			queueSize = (int) Math.ceil(words.size() / wordsSize);
			pool = new ThreadPool(100, 25);

		} catch (IOException e) {
			e.printStackTrace();
		}

		int preCount = 0;
		for (int j = 0; j < 100; j++) {

			pool.start();

			AtomicInteger count = new AtomicInteger(0);
			for (int i = 0; i < queueSize; i++) {
				int start = i * wordsSize;
				int end = (i + 1) * wordsSize;
				List<String> subwords = words.subList(start, end);
				pool.dispatch(new CountWords(subwords, count));
			}

			pool.stop();
			// System.out.println("文字数が12以上の単語の数：" + count);
			if (j != 0)
				assertEquals(count.get(), preCount);
			preCount = count.get();
		}

	}

	@Test
	public void testCountWords_comp2stream() {
		// ストリームを使用した結果と同じかどうか
		try {
			String path = new File(".").getAbsoluteFile().getParent();
			String contents = new String(Files.readAllBytes(Paths.get(path + "/src/jpl8/ch02/ex01/WarAndPeace.txt")),
					StandardCharsets.UTF_8);

			List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

			int wordsSize = 50;
			int queueSize = (int) Math.ceil(words.size() / wordsSize);
			ThreadPool pool = new ThreadPool(100, 25);
			pool.start();

			AtomicInteger count = new AtomicInteger(0);
			for (int i = 0; i < queueSize; i++) {
				int start = i * wordsSize;
				int end = (i + 1) * wordsSize;
				List<String> subwords = words.subList(start, end);
				pool.dispatch(new CountWords(subwords, count));
			}

			pool.stop();
			// System.out.println("文字数が12以上の単語の数：" + count);
			int streamCount = (int) words.stream().filter(i -> i.length() > 12).count();
			assertEquals(streamCount, count.get());

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
