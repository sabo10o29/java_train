package interpret;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

public class ListClass {
	//private static final String PK_NAME = "org.some.classes";
	private static final String PK_NAME ="jpl.ch04.ex01";
	
	public static void main(String... args) throws Exception {
		String packageName = PK_NAME;
		List<Class<?>> classes = getClasses(packageName);
		for (Class<?> class1 : classes) {
			System.out.println(class1.getName());
		}
	}

	/**
	 * パッケージ名を文字列で取得し、パッケージ直下のクラスをリストに詰めて返します。
	 * 
	 * @param packageName
	 *            パッケージ名
	 * @return クラスのリスト
	 * @throws IOException
	 * @throws URISyntaxException
	 * @throws ClassNotFoundException
	 */
	private static List<Class<?>> getClasses(String packageName)
			throws IOException, URISyntaxException, ClassNotFoundException {
		// クラスローダを取得
		ClassLoader cl = Thread.currentThread().getContextClassLoader();

		// パッケージ配下のリソースを取得（複数の場合あり）
		Enumeration<URL> e = cl.getResources(packageName.replace(".", "/"));

		// 返却用のリストを宣言
		List<Class<?>> classes = new ArrayList<>();

		// パッケージ配下のリソースの数だけループ
		for (; e.hasMoreElements();) {

			// リソースのURLを取得
			URL url = e.nextElement();

			// URLをファイルオブジェクトに変換
			File dir = new File(url.getPath());

			// ディレクトリ配下のファイル数分ループ
			for (String path : dir.list()) {

				// ".class"で終わるファイルのみ返却用のリストに追加
				if (path.endsWith(".class")) {
					classes.add(Class.forName(packageName + "." + path.substring(0, path.length() - 6)));
				}
			}
		}
		return classes;
	}
}
