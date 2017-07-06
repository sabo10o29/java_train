package jpl8.ch01.ex04;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;
import java.util.stream.Stream;

import org.junit.Test;

public class ListSubDirTest {

	@Test
	public void testListSubDir_lambda() {

		String path = new File(".").getAbsoluteFile().getParent();
		path += "/src/jpl8/ch01/ex04/";
		File file = new File(path);
		File[] list_original = file.listFiles();
		File[] list_sorted = SortFiles.sortFiles(list_original);
		File[] ans = answer(path);
		assertArrayEquals(list_sorted, ans);
	}

	public File[] answer(String filename) {
		File file = new File(filename);
		File[] listD = file.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
		File[] listF = file.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.isFile();
			}
		});
		Arrays.sort(listD, (f1, f2) -> f1.getPath().compareTo(f2.getPath()));
		Arrays.sort(listF, (f1, f2) -> f1.getPath().compareTo(f2.getPath()));

		File[] result = new File[listD.length + listF.length];
		System.arraycopy(listD, 0, result, 0, listD.length);
		System.arraycopy(listF, 0, result, listD.length, listF.length);

		return result;
	}

}
