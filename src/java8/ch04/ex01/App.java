package java8.ch04.ex01;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		class Greeting {
			private StringProperty text = new SimpleStringProperty("Hello, JavaFx");

			public final StringProperty textProperty() {
				return text;
			}

			public final void setText(String newValue) {
				text.set(newValue);
			}

			public final String getText() {
				return text.get();
			}

		}

		//
		Greeting text = new Greeting();

		//
		Label message = new Label(text.getText());
		message.setFont(new Font(100));
		TextField field = new TextField();
		field.setText(text.getText());
		field.textProperty().addListener((obs, old, newVal) -> {
			text.setText(newVal);
			message.setText(newVal);
		});

		//
		VBox pane = new VBox();
		pane.getChildren().addAll(field, message);
		Scene scene = new Scene(pane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Hello");
		primaryStage.show();

	}

}
