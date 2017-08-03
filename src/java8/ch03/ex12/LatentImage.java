package java8.ch03.ex12;

import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


/**
 * 課題１２：機能拡張してColorTransformerも使用できるようにする
 * @author YoshikazuMurase
 *
 */
public class LatentImage {
	
	private Image in;
	private List<ColorTransformer> pendingOperations = new ArrayList<>();
	
	public LatentImage(Image in){
		this.in = in;
	}
	
	LatentImage transform(UnaryOperator<Color> f){
		pendingOperations.add(ColorTransformerUtil.changeColor(f));
		return this;
	}
	LatentImage transform(ColorTransformer f){
		pendingOperations.add(f);
		return this;
	}
	
	
	public static LatentImage from(Image i){
		return new LatentImage(i);
	}
	
	public Image toImage(){
		int width = (int) in.getWidth();
		int height = (int) in.getHeight();
		WritableImage out = new WritableImage(width, height);
		for(int x = 0; x<width; x++){
			for(int y = 0; y<height; y++){
				Color c = in.getPixelReader().getColor(x, y);
				for(ColorTransformer f : pendingOperations)
					c = f.apply(x, y, c);
				out.getPixelWriter().setColor(x, y, c);
			}
		}
		return out;
	}
	
}
