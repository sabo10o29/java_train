package java8.ch03.ex15;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * 課題15 スレッドごとに実行したさいに線がでる 周辺の処理できない枠の処理
 * 
 * @author YoshikazuMurase
 *
 */
public class LatentImage {

	public Image in;
	public List<ImageTransformer> pendingImageOperations = new ArrayList<>();

	public LatentImage(Image in) {
		this.in = in;
	}

	/**
	 * 座標無視の色変更
	 * 
	 * @param f
	 * @return
	 */
	LatentImage transform(UnaryOperator<Color> f) {
		pendingImageOperations.add(ColorAndImageTransformerUtil.changeFunc2ImageTransformer(f));
		return this;
	}

	/**
	 * 座標を指定
	 * 
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

		Color[][] cImage = transImg2Colors(in); // イメージを２次元配列に変換したもの
		for (ImageTransformer img : pendingImageOperations) {
			Color[][] tmpImage = cImage.clone(); // 変換後の値を一時的に保存しておくためのもの
			for (int x = 1; x < width - 1; x++) {
				for (int y = 1; y < height - 1; y++) {
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
	
	/**
	 * 遅延評価する処理を逐次実行しつつ、それらの処理を並列に実行する
	 * @return
	 */
	public Image toImageParalel() {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();

		Color[][] tmpImage = transImg2Colors(in);
		for (ImageTransformer img : pendingImageOperations)
			tmpImage = parallelTransform(tmpImage, img);
		WritableImage out = new WritableImage(width, height);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				Color c = tmpImage[x][y];
				if (c == null)
					c = Color.WHITE;
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		return out;

	}

	public Color[][] parallelTransform(Color[][] inc, ImageTransformer f) {

		int n = Runtime.getRuntime().availableProcessors();
		int height = inc.length;
		int width = inc[0].length;
		Color[][] out = new Color[height][width];
		try {
			ExecutorService pool = Executors.newCachedThreadPool();
			for (int i = 0; i < n; i++) {
				int fromY = i * height / n;
				int toY = (i + 1) * height / n;
				pool.submit(() -> {
					for (int x = 0; x < width; x++) {
						for (int y = fromY; y < toY; y++) {
							out[x][y] = f.apply(x, y, getColorMatrix(inc, x, y));
						}
					}
				});
			}
			pool.shutdown();
			pool.awaitTermination(1, TimeUnit.HOURS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return out;
	}

	public static Color[][] transImg2Colors(Image in) {
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		Color[][] cImage = new Color[width][height];
		for (int x = 0; x < width; x++)
			for (int y = 0; y < height; y++)
				cImage[x][y] = in.getPixelReader().getColor(x, y);
		return cImage;
	}

	/**
	 * 任意の座標のカラー配列を返すメソッド 指定した座標が端の場合（周囲のカラーを取得できない場合）、白を返す
	 * 
	 * @param in
	 * @param x
	 * @param y
	 * @return
	 */
	public static Color[][] getColorMatrix(Image in, int x, int y) {
		Color[][] m = new Color[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				m[i][j] = Color.WHITE;
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (x + i - 1 > 0 && y + i - 1 > 0 && x + i - 1 < in.getWidth() && y + i - 1 < in.getHeight())
					m[i][j] = in.getPixelReader().getColor(x + i - 1, y + i - 1);
			}
		}
		return m;

	}

	public Color[][] getColorMatrix(Color[][] in, int x, int y) {
		Color[][] m = new Color[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				m[i][j] = Color.WHITE;
			}
		}
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (x + i - 1 > 0 && y + i - 1 > 0 && x + i - 1 < in.length && y + i - 1 < in[0].length)
					m[i][j] = in[x + i - 1][y + i - 1];
			}
		}
		return m;

	}

}
