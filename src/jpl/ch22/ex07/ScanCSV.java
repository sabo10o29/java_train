package jpl.ch22.ex07;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class ScanCSV {

	public static List<String[]> readCSVTable(Readable source, int numCells) throws IOException {
		int CELLS = numCells;
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();
		String p = "([^!\"#$%&'()*+,-./:;<=>?@[]^_`{]+$]*)";
		String exp = "^" + p + "," + p + "," + p + "," + p + "";
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			String line = in.findInLine(pat);
			if (line != null) {
				String[] cells = new String[CELLS];
				MatchResult match = in.match();
				for (int i = 0; i < CELLS; i++) {
					cells[i] = match.group(i + 1);
				}
				vals.add(cells);
				try {
					in.nextLine(); // 改行を読み飛ばし
				} catch (NoSuchElementException e) {
				}
			} else {
				throw new IOException("input format error");
			}
		}

		IOException ex = in.ioException();
		if (ex != null) {
			throw ex;
		}
		return vals;
	}

	public static void main(String[] args) {
		String current = new File(".").getAbsoluteFile().getParent();
		String input = current + "/src/jpl/ch22/ex07/input.csv";
		File file = new File(input);

		int numCells = 4;

		System.out.println("セル数" + numCells + "でcsvファイルを読み込みます。");
		System.out.println("範囲外の値は無視されます。");
		try {
			FileReader filereader = new FileReader(file);
			List<String[]> list = ScanCSV.readCSVTable(filereader, numCells);
			System.out.println("ファイルの内容を表示");
			for (String[] strs : list) {
				for (int i = 0; i < strs.length; i++) {
					System.out.print(strs[i] + " ");
				}
				System.out.println();
			}
		} catch (FileNotFoundException e) {
			System.out.println("ファイルが存在しません");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
