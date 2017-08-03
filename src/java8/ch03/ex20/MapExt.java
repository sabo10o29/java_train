package java8.ch03.ex20;

import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class MapExt {

	public static <T, U> List<U> map(List<T> future, Function<T, U> func) {

		List<U> result = new ArrayList<>();
		for (T t : future) {
			result.add(func.apply(t));
		}
		return result;

	}

	public static void main(String[] args) {

		//フォントの名前リストをFont型のリストへ変換する
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String fonts[] = ge.getAvailableFontFamilyNames();

		List<String> flist = Arrays.asList(fonts);
		List<Font> resutl = MapExt.map(flist, (t) -> {
			Font font = new Font(t, Font.ITALIC, 20);
			return font;
		});

		for (Font f : resutl) {
			System.out.println(f.getName() + ": Size = " + f.getSize() + ", Style = " + f.getStyle());
		}

	}

}
