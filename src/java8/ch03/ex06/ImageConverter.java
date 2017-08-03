package java8.ch03.ex06;


import java.util.function.BiFunction;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

/**
 * P65のメソッドを実装する
 * @author YoshikazuMurase
 *
 */
public class ImageConverter {
	/**
	 * 
	 * @param in 入力のイメージ画像
	 * @param f ColorとTを用いて新しくColor型を出力する関数
	 * @param arg BiFunctionに必要な変換に対する増幅値
	 * @return
	 */
	public static <T> Image transform(Image in, BiFunction<Color, T, Color> f, T arg){
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for(int x=0; x<width; x++){
			for(int y=0; y<height; y++){
				Color c = in.getPixelReader().getColor(x, y);
				out.getPixelWriter().setColor(x, y, f.apply(c, arg));
			}
		}
		return out;
	}
	
	public static void main(String[] args) {
		
	}

}
