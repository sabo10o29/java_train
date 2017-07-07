package java8.ch01.ex02;

import java.io.File;
import java.io.FileFilter;
import java.util.Arrays;

/**
 * 指定したディレクトリのサブディレクトリを返す ラムダ式とメソッド参照の両方で実現する
 * 
 * @author YoshikazuMurase
 *
 */
public class ListSubDir {

	public static void main(String[] args) {

		String path = new File(".").getAbsoluteFile().getParent();
		File file = new File(path);

		System.out.println(path + "のサブディレクトリをラムダ式で表示します。");
		Arrays.stream(ListSubDir.listSubDir_lambda(file)).forEach(arr -> System.out.println(arr));

		System.out.println(path + "のサブディレクトリをメソッド参照で表示します。");
		Arrays.stream(ListSubDir.listSubDir_method(file)).forEach(arr -> System.out.println(arr));

	}

	public static File[] listSubDir_lambda(File file) {
		return file.listFiles(targetFile -> {
			return targetFile.isDirectory();
		});
	}

	public static File[] listSubDir_method(File file) {
		return file.listFiles(File::isDirectory);
	}

}
