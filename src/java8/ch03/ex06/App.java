package java8.ch03.ex06;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// イメージ画像の読み込み
		String url = "https://upload.wikimedia.org/wikipedia/en/2/24/Lenna.png";
		Image in = new Image(url);
		ImageView before = new ImageView(in);
		
		// 読み込んだ画像を変換
		Image out = ImageConverter.transform(in, (c, factor) -> c.deriveColor(0, 1, factor, 1), 4);
		ImageView after = new ImageView(out);

		// Paneに読み込んだ画像をセット
		HBox imageBox = new HBox(); // Paneの作成：Paneの一種、javafxではPaneをシーンに貼付けて描画を行う
		imageBox.setAlignment(Pos.TOP_CENTER);
		imageBox.setPadding(new Insets(30, 30, 30, 30));
		imageBox.getChildren().add(before);
		imageBox.getChildren().add(after);

		// シーンに貼付けて描画処理
		Scene scene = new Scene(imageBox); // シーンの作成：ステージには見えないコンテナ、シーンに貼付ける形で描画を行う
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
