package java8.ch03.ex15;

import java.util.function.UnaryOperator;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * 
 * @author YoshikazuMurase
 *
 */
public class ImageConverter {

	public static Image transform(Image in, UnaryOperator<Color> f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y)));
		return out;
	}

	public static Image transform(Image in, ColorTransformer f) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y, f.apply(x, y, in.getPixelReader().getColor(x, y)));
		return out;
	}

	/**
	 * 任意のサイズの任意の色の枠を生成するColorTransferメソッドを生成するメソッド
	 * 指定したサイズが画像のサイズ以上の場合にはIllegalArgumentExceptionを返す
	 * 
	 * @param width
	 *            枠の幅
	 * @param clor
	 *            枠の色
	 * @return
	 */
	public static ColorTransformer getFrameColorTransfer(Image in, int width, Color color)
			throws IllegalArgumentException {
		if (in.getHeight() < width || in.getWidth() < width)
			throw new IllegalArgumentException();
		return (x, y, c) -> {
			if (x <= width || y <= width || x >= in.getWidth() - width || y >= in.getHeight() - width)
				return color;
			return c;
		};
	}

	/**
	 * モザイク処理を行うImageTransformerを返すメソッド
	 * @return
	 */
	public static ImageTransformer getMosaicImageTransfer(){
		return (c, d, m) -> {
			 double r = 0.0;
		     double g = 0.0;
		     double b = 0.0;
		     for (int x = 0; x < 3; x++) {
		    	 for (int y = 0; y < 3; y++) {
		                r += m[x][y].getRed();
		                g += m[x][y].getGreen();
		                b += m[x][y].getBlue();
		            }
		        }
			return Color.color(r/9, g/9, b/9);
		};
	}
	
	/**
	 * エッジ検出処理を行うImageTransformerを返すメソッド
	 * @return
	 */
	public static ImageTransformer getEdgeDetectImageTransfer(){
		return (c, d, m) -> {
			double r = 4*m[1][1].getRed()-m[1][0].getRed()-m[1][2].getRed()-m[0][1].getRed()-m[2][1].getRed();
		    double g = 4*m[1][1].getGreen()-m[1][0].getGreen()-m[1][2].getGreen()-m[0][1].getGreen()-m[2][1].getGreen();
		    double b = 4*m[1][1].getBlue()-m[1][0].getBlue()-m[1][2].getBlue()-m[0][1].getBlue()-m[2][1].getBlue();
			return Color.color(Math.max(Math.min(r  , 1.0), 0.0),
		            Math.max(Math.min(g, 1.0), 0.0),
		            Math.max(Math.min(b, 1.0), 0.0));
		};
	}

}
