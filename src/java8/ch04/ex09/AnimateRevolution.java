package java8.ch04.ex09;

import javafx.animation.Interpolator;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Ellipse;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AnimateRevolution extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// シーングラフを作成
		Group root = new Group();
		Scene scene = new Scene(root);

		Ellipse ellipse = new Ellipse(100, 50);
		ellipse.setFill(null);
		ellipse.setStroke(Color.RED);
		ellipse.setLayoutX(200);
		ellipse.setLayoutY(200);
		ellipse.setOpacity(0.5);
		root.getChildren().add(ellipse);

		// シーングラフに赤色の円を追加
		Circle pluto = new Circle(5, Color.BLUE);
		root.getChildren().add(pluto);

		// 赤色の円に移動アニメーションを追加
		PathTransition pathTransition = new PathTransition();
		pathTransition.setNode(pluto); // アニメーション対象ノードを登録
		pathTransition.setDuration(Duration.millis(5000)); // アニメーション1サイクル分の時間を設定
		pathTransition.setPath(ellipse); // アニメーションの軌道を設定
		pathTransition.setInterpolator(Interpolator.LINEAR); // アニメーション補完方法を線形に設定
		pathTransition.setCycleCount(PathTransition.INDEFINITE); // 無限アニメーションに設定

		// ウィンドウ表示
		primaryStage.setScene(scene);
		primaryStage.show();

		// アニメーション開始
		pathTransition.play();
	}

}