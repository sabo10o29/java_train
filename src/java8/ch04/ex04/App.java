package java8.ch04.ex04;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		Pane pane = new Pane();
		Scene scene = new Scene(pane);

		Circle circle = new Circle(50, Color.AQUA);
		circle.centerXProperty().bind(Bindings.divide(scene.widthProperty(), 2.0));
		circle.centerYProperty().bind(Bindings.divide(scene.heightProperty(), 2.0));
		circle.radiusProperty().bind(Bindings.divide(scene.widthProperty(), 2.0));

		pane.getChildren().addAll(circle);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Circle");
		primaryStage.show();

	}

}
