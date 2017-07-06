package jpl.ch22.ex11;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;

public class ScanCSV {

	public static List<String[]> readCSVTable(FileReader source, int numCells) throws IOException {

		List<String[]> vals = new ArrayList<String[]>();

		StreamTokenizer st = new StreamTokenizer(source);
		st.eolIsSignificant(true);

		// 区切り文字をカウント
		// 指定したセル以下の場合：String配列にトークンを挿入
		// 指定したセル以上の場合：文字をスキップ
		// 改行が発生した場合：区切り文字のカウントを０に戻す＆リストに現在の配列をaddする
		// ＊空文字はスキップされる
		int token;
		int tokenCount = 0;
		String[] strs = new String[numCells];
		while ((token = st.nextToken()) != StreamTokenizer.TT_EOF) {
			switch (token) {
			case StreamTokenizer.TT_EOL:
				if (tokenCount != 0) {
					tokenCount = 0; // カウントリセット
					vals.add(strs); // トークンの配列をリストに追加
				}
				strs = new String[numCells]; // 配列の初期化
				break;
			case StreamTokenizer.TT_NUMBER:
				if (tokenCount != numCells) {
					strs[tokenCount] = String.valueOf(st.nval);
					tokenCount++;
				}
				break;
			case StreamTokenizer.TT_WORD: // 文字を検出した場合の処理
				if (tokenCount != numCells) {
					strs[tokenCount] = st.sval;
					tokenCount++;
				}
				break;
			default:
				break;
			}
		}
		vals.add(strs); // 最後の行を追加
		return vals;
	}

	public static void main(String[] args) {
		String current = new File(".").getAbsoluteFile().getParent();
		String input = current + "/src/jpl/ch22/ex11/input2.csv";
		File file = new File(input);

		int numCells = 4;

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

			System.out.println("IOError");
			e.printStackTrace();
		}

	}

}
