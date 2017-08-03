package java8.ch03.ex12;
import java.util.function.UnaryOperator;
import javafx.scene.paint.Color;

public class ColorTransformerUtil {

	/**
	 * ColorTransformerを合成するメソッド
	 * 
	 * @param a
	 * @return
	 */
	public static ColorTransformer compose(ColorTransformer a, ColorTransformer b) {
		return (x, y, color) -> a.apply(x, y, b.apply(x, y, color));
	}

	/**
	 * 座標を無視して色を変換するメソッド
	 * 
	 * @param func
	 * @return
	 */
	public static ColorTransformer changeColor(UnaryOperator<Color> func) {
		return (x, y, color) -> func.apply(color);
	}

}
