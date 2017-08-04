package java8.ch04.ex07;

import static javafx.beans.binding.Bindings.*;

import java.util.function.BiFunction;
import java.util.function.Function;

import org.omg.CORBA.IMP_LIMIT;

import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * コントロールの境界値設定にはRegionクラスのboederPropertyを設定することで可能
 * https://stackoverflow.com/questions/27712213/how-do-i-make-a-simple-solid-border-around-a-flowpane-in-javafx
 * @author YoshikazuMurase
 *
 */
public class BindingDemo3 extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public static <T, R> ObservableValue<R> observe(Function<T, R> f, ObservableValue<T> t){
		return createObjectBinding(() -> f.apply(t.getValue()),t);
	}
	
	public static <T, U, R> ObservableValue<R> observe(BiFunction<T, U, R> f, ObservableValue<T> t, ObservableValue<U> u){
		return Bindings.createObjectBinding(()->f.apply(t.getValue(), u.getValue()), t);
	}
	
	public void start(Stage stage) {
		MenuBar test = new MenuBar();
		test.borderProperty().set(new Border(new BorderStroke(Color.BLACK, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
		 // --- Menu File
        Menu menuFile = new Menu("File");
        // --- Menu Edit
        Menu menuEdit = new Menu("Edit");
        // --- Menu View
        Menu menuView = new Menu("View");
        test.getMenus().addAll(menuFile, menuEdit, menuView);
		
		Text txt = new Text("状態：　");
		Button smaller = new Button("Smaller");
		Button larger = new Button("Larger");
		Rectangle gauge = new Rectangle(0, 5, 50, 15);
		Rectangle outline = new Rectangle(0, 5, 100, 15);
		outline.setFill(null);
		outline.setStroke(Color.BLACK);
		Pane pane = new Pane();
		pane.getChildren().addAll(gauge, outline);
		smaller.setOnAction(event -> gauge.setWidth(gauge.getWidth() - 10));
		larger.setOnAction(event -> gauge.setWidth(gauge.getWidth() + 10));
		//１つのプロパティ値に応じてバインディングする
		smaller.disableProperty().bind(observe(val -> val <= 0, gauge.widthProperty().asObject()));
		larger.disableProperty().bind(observe(t -> t >= 100, gauge.widthProperty().asObject()));
		//２つのプロパティ値に応じてテキストをバインディングする
		txt.textProperty().bind(observe((val1, val2) -> {
			if(val1||val2)
				return "限界値です";
			return "許容範囲内です";
		}, larger.disableProperty(), smaller.disabledProperty()));
		VBox base = new VBox(10);
		HBox box = new HBox(10);
		box.getChildren().addAll(smaller, pane, larger);
		base.getChildren().addAll(test, txt,box);
		Scene scene = new Scene(base);
		stage.setScene(scene);
		stage.show();
		
	}
}
