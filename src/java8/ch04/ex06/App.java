package java8.ch04.ex06;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		BorderPane pane = new BorderPane();
		Scene scene = new Scene(pane);

		Button top = new Button("Top");
		BorderPane.setAlignment(top, Pos.CENTER);
		pane.setTop(top);
		pane.setLeft(new Button("Left"));
		pane.setCenter(new Button("Center"));
		pane.setRight(new Button("Right"));
		Button bottom = new Button("Bottom");
		BorderPane.setAlignment(bottom, Pos.CENTER);
		pane.setBottom(bottom);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Layout");
		primaryStage.show();

	}

}
