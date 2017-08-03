package java8.ch03.ex13;
import java.util.function.UnaryOperator;
import javafx.scene.paint.Color;

public class ColorAndImageTransformerUtil {

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
	 * UnaryOperator<Color> -> ColorTransformer
	 * @param func
	 * @return
	 */
	public static ColorTransformer changeFunc2ColorTransformer(UnaryOperator<Color> func) {
		return (x, y, color) -> func.apply(color);
	}
	/**
	 * ColorTransformer -> ImageTransformer
	 * @param func
	 * @return
	 */
	public static ImageTransformer changeColor2ImageTransformer(ColorTransformer func){
		return (x, y, m) -> func.apply(x, y, m[1][1]);
	}
	/**
	 * (UnaryOperator<Color> -> ImageTransformer
	 * @param func
	 * @return
	 */
	public static ImageTransformer changeFunc2ImageTransformer(UnaryOperator<Color> func){
		return (x, y, m) -> func.apply(m[1][1]);
	}

}
