package jpl8.ch01.ex03;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.stream.Stream;

/**
 * 拡張子がエンクロージングスコープからキャプチャされる
 * 
 * @author YoshikazuMurase
 *
 */
public class ListSubDir {

	public static void main(String[] args) {

		String path = new File(".").getAbsoluteFile().getParent();
		File file = new File(path);
		String name = "png";

		File[] list = listSubDir(file, name);
		for (File str : list) {
			System.out.println(str);
		}

	}

	public static File[] listSubDir(File f, String extension) {
		String[] list = f.list((file, name) -> name.endsWith(extension));
		return Stream.of(list).map(name -> new File(name)).toArray(i -> new File[i]);
	}

}
