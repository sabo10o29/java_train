package java8.ch02.ex02;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * マッチする単語のうち、最初の５個だけを求めるメソッド
 * 
 * @author YoshikazuMurase
 *
 */
public class CountWords {

	public static void main(String[] args) {

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

	}

}
