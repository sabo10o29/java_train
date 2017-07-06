package jpl8.ch02.ex02;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import Hello.HelloWorld;
import StdCap.StdoutCapture;

public class CountWordsTest {

	@Test
	public void test() {
		String[] expected = new String[] { "検出した単語:beginning", "検出した単語:sitting", "検出した単語:nothing", "検出した単語:reading",
				"検出した単語:pictures" };

		StdoutCapture sc = new StdoutCapture();
		sc.start();

		try {
			String path = new File(".").getAbsoluteFile().getParent();
			String contents = new String(Files.readAllBytes(Paths.get(path + "/src/jpl8/ch02/ex01/alice.txt")),
					StandardCharsets.UTF_8);

			List<String> words = Arrays.asList(contents.split("[\\P{L}]+"));

			long count = words.stream().filter(w -> {
				if (w.length() > 6) {
					System.out.println("検出した単語:" + w);
					return true;
				}
				return false;
			}).limit(5).count();

		} catch (IOException e) {
			e.printStackTrace();
		}

		sc.stop();
		sc.assertEquals(expected);
	}

}
