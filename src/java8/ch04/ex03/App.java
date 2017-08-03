package java8.ch04.ex03;

import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
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

			private String text = new String("Hello, JavaFx");
			private StringProperty txtProperty = null;
			private Integer val = 0;
			private IntegerProperty valProperty = null;
			private Object obj = new Object();
			private ObjectProperty objProperty = null;
			
			public final ObjectProperty objProperty() {
				if (objProperty == null)
					objProperty = new SimpleObjectProperty(0);
				return objProperty;
			}
			public final void setObj(Object newValue) {
				if (objProperty == null) {
					obj = newValue;
				} else if(!objProperty.getValue().equals(newValue)){
					objProperty.set(newValue);
				}
			}
			public final Object getObj() {
				if (objProperty == null) {
					return obj;
				} else {
					return objProperty.get();
				}
			}
			
			public final IntegerProperty valProperty() {
				if (valProperty == null)
					valProperty = new SimpleIntegerProperty(0);
				return valProperty;
			}
			public final void setVal(Integer newValue) {
				if (valProperty == null) {
					val = newValue;
				} else if(!valProperty.getValue().equals(newValue)){
					valProperty.set(newValue);
				}
			}
			public final Integer getVal() {
				if (txtProperty == null) {
					return val;
				} else {
					return valProperty.get();
				}
			}
			
			public final StringProperty textProperty() {
				if (txtProperty == null)
					txtProperty = new SimpleStringProperty(text);
				return txtProperty;
			}
			public final void setText(String newValue) {
				if (txtProperty == null) {
					text = newValue;
				} else if(!txtProperty.getValue().equals(newValue)){
					txtProperty.set(newValue);
				}
			}
			public final String getText() {
				if (txtProperty == null) {
					return text;
				} else {
					return txtProperty.get();
				}
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
