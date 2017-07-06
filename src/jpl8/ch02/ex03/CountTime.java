package jpl8.ch02.ex03;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * parallelStreamとStreamでの計算速度の違いを求める
 * 
 * @author YoshikazuMurase
 *
 */
public class CountTime {

	int wordSize;

	public CountTime(int i) {
		wordSize = i;
	}

	public int coutWords_stream(List<String> words) {

		long count = words.stream().filter(w -> w.length() > wordSize).count();
		return (int) count;
	}

	public int countWords_parallel(List<String> words) {
		long count = words.parallelStream().filter(w -> w.length() > wordSize).count();
		return (int) count;
	}

	public static void main(String[] args) {

		long start, end;
		double time;
		int count;

		CountTime test = new CountTime(12);
		String path = new File(".").getAbsoluteFile().getParent();
		String contents;
		try {
			contents = new String(Files.readAllBytes(Paths.get(path + "/src/jpl8/ch02/ex03/WarAndPeace.txt")),
					StandardCharsets.UTF_8);
			List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

			start = System.nanoTime();
			count = test.coutWords_stream(words);
			end = System.nanoTime();
			time = (end - start) / 1000000000d;
			System.out.println("通常時");
			System.out.printf("カウント：%d, 処理時間：%.10f秒", count, time);
			
			System.out.printf("\n\n");
			
			start = System.nanoTime();
			count = test.countWords_parallel(words);
			end = System.nanoTime();
			time = (end - start) / 1000000000d;
			System.out.println("並列実行時");
			System.out.printf("カウント：%d, 処理時間：%.10f秒", count, time);
			
			

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
