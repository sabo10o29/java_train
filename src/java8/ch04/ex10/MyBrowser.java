package java8.ch04.ex10;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class MyBrowser extends Application {

	private WebView view = new WebView();
	private WebEngine engine = view.getEngine();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		// ブラウザの初期化
		primaryStage.setTitle("MyBrowser");
		Group root = new Group();

		// 検索バーの初期化
		HBox bar = new HBox();
		bar.setAlignment(Pos.CENTER);
		TextField url = new TextField("URL");
		Button button = new Button("Load");
		button.setOnAction((e) -> {
			String urlstr = url.getText();
			engine.load(urlstr);
			System.out.println("loading web page");
		});
		Button back = new Button("Back");
		back.setOnAction((e) -> {
			engine.getHistory().go(-1);
			System.out.println("back web page");
		});
		back.setDisable(true);
		//履歴にバックボタンをバインディング
		//０以外の場合にはボタンを有効にする
		engine.getHistory().currentIndexProperty().addListener((observable, oldValue, newValue) -> {
			if (newValue.intValue() == 0) {
				back.setDisable(true);
			} else {
				back.setDisable(false);
			}

		});

		bar.getChildren().add(url);
		bar.getChildren().add(button);
		bar.getChildren().add(back);

		VBox vbox = new VBox();
		vbox.getChildren().add(bar);
		vbox.getChildren().add(view);

		root.getChildren().add(vbox);
		Scene scene = new Scene(root, 800, 600);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
