package java8.ch03.ex15;

import javafx.scene.paint.Color;

@FunctionalInterface
public interface ImageTransformer {
	
	/**
	 * ColorTransferを包括する関数インタフェース
	 * @param x
	 * @param y
	 * @param matrix
	 * @return
	 */
	Color apply(int x, int y, Color[][] matrix);
	
}
