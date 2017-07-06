package jpl8.ch01.ex04;

import java.io.File;
import java.util.Arrays;

public class SortFiles {

	public static void main(String[] args) {
		String path = new File(".").getAbsoluteFile().getParent();
		path += "/src/jpl8/ch01/ex04/";
		System.out.println(path);
		File file = new File(path);
		File[] list_original = file.listFiles();
		File[] list_sorted = sortFiles(list_original);

		System.out.println("＊＊＊＊元の並び順＊＊＊＊");
		for (File f : list_original) {
			System.out.println(f.getName());
		}
		System.out.println("＊＊＊＊ソートされた並び順＊＊＊＊");
		for (File f : list_sorted) {
			System.out.println(f.getName());
		}

	}

	public static File[] sortFiles(File[] files) {

		File[] clone = files.clone();

		Arrays.sort(clone, (f1, f2) -> {
			if (f1.isDirectory() && f2.isFile())
				return -1;
			else if (f1.isFile() && f2.isDirectory())
				return 1;
			else {
				return f1.compareTo(f2);
			}
		});

		return clone;
	}

}
