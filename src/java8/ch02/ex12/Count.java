package java8.ch02.ex12;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Count {

	public static void countShortWords(Stream<String> words) {

		AtomicIntegerArray shortWords = new AtomicIntegerArray(12);
		words.parallel().forEach(s -> {
			if (s.length() < 12)
				shortWords.getAndIncrement(s.length());
		});
		IntStream.range(1, 12).forEach(i -> System.out.println(shortWords.get(i)));

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
