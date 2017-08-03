package java8.ch03.ex13;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * 課題１２：機能拡張してColorTransformerも使用できるようにする Color[][]を生成して逐次変換を行う
 * 
 * @author YoshikazuMurase
 *
 */
public class LatentImage {

	private Image in;
	private List<ImageTransformer> pendingImageOperations = new ArrayList<>();

	public LatentImage(Image in) {
		this.in = in;
	}

	
	/**
	 * 座標無視の色変更
	 * @param f
	 * @return
	 */
	LatentImage transform(UnaryOperator<Color> f) {
		pendingImageOperations.add(ColorAndImageTransformerUtil.changeFunc2ImageTransformer(f));
		return this;
	}
	/**
	 * 座標を指定
	 * @param f
	 * @return
	 */
	LatentImage transform(ColorTransformer f) {
		pendingImageOperations.add(ColorAndImageTransformerUtil.changeColor2ImageTransformer(f));
		return this;
	}
	/**
	 * 
	 * @param f
	 * @return
	 */
	LatentImage transform(ImageTransformer f) {
		pendingImageOperations.add(f);
		return this;
	}

	public static LatentImage from(Image i) {
		return new LatentImage(i);
	}

	public Image toImage() {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Color c = in.getPixelReader().getColor(x, y);
				for (ImageTransformer f : pendingImageOperations)
					c = f.apply(x, y, getColorMatrix(in, x, y));
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		return out;
	}

	/**
	 * 一つのtransformer処理が終了するまで次のTransformerを実施しない
	 * 
	 * @return
	 */
	public Image toImageSequ() {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		
		Color[][] cImage = transImg2Colors(in);
		for (ImageTransformer img : pendingImageOperations) {
			Color[][] tmpImage = cImage.clone();
			for (int x = 1; x < width-1; x++) {
				for (int y = 1; y < height-1; y++) {
					Color[][] colors = getColorMatrix(in, x, y);
					Color c = img.apply(x, y, colors);
					tmpImage[x][y] = c;
				}
			}
			cImage = tmpImage.clone();
		}
		
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				out.getPixelWriter().setColor(x, y, cImage[x][y]);
		return out;
	}
	
	public static Color[][] transImg2Colors(Image in){
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		Color[][] cImage = new Color[width][height];
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				cImage[x][y] = in.getPixelReader().getColor(x, y);
		return cImage;
	}
	
	public static Color[][] getColorMatrix(Image in, int x, int y){
		Color[][] m = new Color[3][3];
		m[0][0] = in.getPixelReader().getColor(x-1, y-1);
		m[1][0] = in.getPixelReader().getColor(x, y-1);
		m[2][0] = in.getPixelReader().getColor(x+1, y-1);
		m[0][1] = in.getPixelReader().getColor(x-1, y);
		m[1][1] = in.getPixelReader().getColor(x, y);
		m[2][1] = in.getPixelReader().getColor(x+1, y);
		m[0][2] = in.getPixelReader().getColor(x-1, y+1);
		m[1][2] = in.getPixelReader().getColor(x, y+1);
		m[2][2] = in.getPixelReader().getColor(x+1, y+1);
		return m;
		
	}

}
