package java8.ch01.ex03;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.stream.Stream;

import org.junit.Test;

import java8.ch01.ex03.ListSubDir;

public class ListSubDirTest {

	@Test
	public void testListSubDir() {

		String path = new File(".").getAbsoluteFile().getParent();
		File file = new File(path);
		String ex = "png";
		File[] list = ListSubDir.listSubDir(file, ex);
		File[] ans = answer(file, ex);
		System.out.println(list[0].getPath() + "test" + ans[0].getPath());
		assertArrayEquals(list, ans);

	}

	public File[] answer(File file, String ex) {

		FilenameFilter filter = new FilenameFilter() {

			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(ex);
			}
		};
		return Stream.of(file.list(filter)).map(i -> new File(i)).toArray(i -> new File[i]);
	}

}
