package java8.ch01.ex02;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileFilter;

import org.junit.Test;

import java8.ch01.ex02.ListSubDir;


public class ListSubDirTest {

	@Test
	public void testListSubDir_lambda() {

		String path = new File(".").getAbsoluteFile().getParent();
		File[] list = ListSubDir.listSubDir_lambda(new File(path));
		File[] ans = answer(path);
		assertArrayEquals(list, ans);

	}

	@Test
	public void testListSubDir_method() {
		String path = new File(".").getAbsoluteFile().getParent();
		File[] list = ListSubDir.listSubDir_method(new File(path));
		File[] ans = answer(path);
		assertArrayEquals(list, ans);

	}

	public File[] answer(String filename) {
		File file = new File(filename);
		File[] list = file.listFiles(new FileFilter() {

			@Override
			public boolean accept(File pathname) {
				return pathname.isDirectory();
			}
		});
		return list;
	}

}
