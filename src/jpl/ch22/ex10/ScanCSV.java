package jpl.ch22.ex10;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class ScanCSV {

	public static void splitCSVTable(Readable source) {
		Scanner in = new Scanner(source);
		String de = "(,|#.*(\r|\n|\r\n)+)"; // デリミタ
		in.useDelimiter(de);
		while (in.hasNext()) {
			String str = in.next();
			System.out.println("トークン：　" + str);
		}
	}

	public static void main(String[] args) {
		String current = new File(".").getAbsoluteFile().getParent();
		String input = current + "/src/jpl/ch22/ex10/input2.csv";
		File file = new File(input);

		FileReader filereader = null;
		try {
			filereader = new FileReader(file);
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		splitCSVTable(filereader);

	}

}
