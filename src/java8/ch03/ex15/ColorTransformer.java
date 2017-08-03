package java8.ch03.ex15;

import javafx.scene.paint.Color;

@FunctionalInterface
public interface ColorTransformer {

	Color apply(int x, int y, Color colorAtXY);
	
	
}
