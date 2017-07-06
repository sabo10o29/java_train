package jpl8.ch02.ex13;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Count {

	/**
	 * フィルターで１２文字以下をカウントしたあとで、文字数に応じて出現回数をカウントする。
	 * 
	 * @param words
	 */
	public static void countShortWords(Stream<String> words) {

		Map<Object, Long> result = words.parallel().filter(w -> w.length() < 12)
				.collect(Collectors.groupingBy(e -> e.length(), Collectors.counting()));

		// 表示
		for (Entry<Object, Long> s : result.entrySet()) {
			System.out.println("Word length=" + s.getKey());
			System.out.println("Word counts=" + s.getValue());
			System.out.println();
		}
	}

	public static void main(String[] args) {
		String path = new File(".").getAbsoluteFile().getParent();
		String contents;
		try {
			contents = new String(Files.readAllBytes(Paths.get(path + "/src/jpl8/ch02/ex03/WarAndPeace.txt")),
					StandardCharsets.UTF_8);
			List<String> list = Arrays.asList(contents.split("[\\P{L}]+"));
			Stream<String> words = list.stream();
			Count.countShortWords(words);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
