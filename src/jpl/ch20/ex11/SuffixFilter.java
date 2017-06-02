package jpl.ch20.ex11;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;

/**
 * ディレクトリと接尾語を受け取って一覧を表示する
 * 
 * @author YoshikazuMurase
 *
 */
public class SuffixFilter implements FilenameFilter {

	private String suffix;

	public SuffixFilter(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public boolean accept(File dir, String name) {

		File f = new File(dir, name);
		String str = f.getName();

		// ファイルの場合
		if (f.isFile()) {
			// 文字列から拡張しを取り除く
			int point = str.lastIndexOf(".");
			if (point != -1) {
				str = str.substring(0, point);
			}
		}

		// 書かない
		// 接尾語の検索
		if (str.endsWith(suffix)) {
			return true;
		} else {
			return false;
		}

	}

	public static void main(String[] args) {

		String current = new File(".").getAbsoluteFile().getParent();
		String input = current + "/src/jpl/";
		String suffix = "20";

		File dir = new File(input);
		String[] files = dir.list(new SuffixFilter(suffix));

		System.out.println("Search dir: " + input);
		System.out.println(files.length + "dir(s):");
		for (String file : files)
			System.out.println("\t" + file);
	}

}
