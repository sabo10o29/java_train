package java8.ch03.ex08;

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

	public static Image transform(Image in, UnaryOperator<Color> f){
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				out.getPixelWriter().setColor(x, y, f.apply(in.getPixelReader().getColor(x, y)));
		return out;
	}
	
	public static Image transform(Image in, ColorTransformer f){
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for(int x=0; x<width; x++)
			for(int y=0; y<height; y++)
				out.getPixelWriter().setColor(x, y, f.apply(x, y, in.getPixelReader().getColor(x, y)));
		return out;
	}
	
	/**
	 * 任意のサイズの任意の色の枠を生成するColorTransferメソッドを生成するメソッド
	 * 指定したサイズが画像のサイズ以上の場合にはIllegalArgumentExceptionを返す
	 * @param width 枠の幅
	 * @param clor 枠の色
	 * @return
	 */
	public static ColorTransformer getFrameColorTransfer(Image in, int width, Color color) throws IllegalArgumentException{
		if(in.getHeight()<width||in.getWidth()<width)
			throw new IllegalArgumentException();
		return (x,y,c)->{
			if(x<=width||y<=width||x>=in.getWidth()-width||y>=in.getHeight()-width)
				return color;
			return c;
		};
	}
	
}
