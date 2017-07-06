package jpl.ch22.ex09;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class ScanCSV {

	public static final int LOOP = 10000;
	public static final int CELL = 4;

	public static double[] benchMark(String regex) {
		String l = "64817023894782391748738972897483721413249871204707217498237498748372198478977432817412387483274983271489728975739275759842375892738957382574387598437958743827587957957987872358,"
				+ "749327148327498372149879847182974983174893721894789274982317849723897497287897198473829478397489723194712948735743275894375982475873598742985743987594827584759472587857928757423,"
				+ "473289749832749837219487392817498237148748327147894732974938749832714987219487328743928174387498327149872198475743897598437598473285743875275874832758745723758437584375827358755,"
				+ "747328743287493274983271498732948179874982317497918274987324973948728391748974983174987148278972189789791287475843257395732589759872857439857487598758237589378745873258735725834";
		String s = "1,2,3,4";

		double[] result = new double[2];

		long sum = 0;
		for (int i = 0; i < LOOP; i++) {
			sum += timeCount(new StringReader(l), regex);
		}
		result[0] = sum / 1000000f / LOOP;

		sum = 0;
		for (int i = 0; i < LOOP; i++) {
			sum += timeCount(new StringReader(s), regex);
		}
		result[1] = sum / 1000000f / LOOP;

		return result;

	}

	public static long timeCount(Readable source, String regex) {
		long startTime = System.nanoTime();
		try {
			ScanCSV.readCSVTable(source, regex);
		} catch (IOException e) {
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		return endTime - startTime;
	}

	public static List<String[]> readCSVTable(Readable source, String exp) throws IOException {
		Scanner in = new Scanner(source);
		List<String[]> vals = new ArrayList<String[]>();
		Pattern pat = Pattern.compile(exp, Pattern.MULTILINE);
		while (in.hasNextLine()) {
			String line = in.findInLine(pat);
			if (line != null) {
				String[] cells = new String[CELL];
				MatchResult match = in.match();
				for (int i = 0; i < CELL; i++) {
					cells[i] = match.group(i + 1);
				}
				vals.add(cells);
				if (in.hasNextLine())
					in.nextLine(); // 改行を読み飛ばし
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
		int numCells = 4;

		String[] pats = { "^([^,]*),([^,]*),([^,]*),([^,]*)", "^([^,]*),{1}([^,]*),{1}([^,]*),{1}([^,]*)",
				"^([^,]+),([^,]+),([^,]+),([^,]+)", "^([^,]+),([^,]+),([^,]+),([^,]?)" };

		String current = new File(".").getAbsoluteFile().getParent();
		String input = current + "/src/jpl/ch22/ex08/input3.csv";
		File file = new File(input);

		try {
			FileReader filereader = new FileReader(file);

			for (String p : pats) {
				double[] scores = ScanCSV.benchMark(p);
				System.out.println(p + "の効率性計測");
				System.out.println("最短一致量指定子:" + scores[0]);
				System.out.println("最長一致量指定子:" + scores[1]);
			}

		} catch (FileNotFoundException e) {
			System.out.println("ファイルが存在しません");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}

	}

}
