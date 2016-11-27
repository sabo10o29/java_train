package jpl.ch06.ex04;
import java.awt.Color;

public class ColorEnum {
	
	enum Colorenum {
		RED(Color.red),
		YELLOW(Color.yellow),
		BLUE(Color.blue);
		
		private Color color;
		Colorenum(Color _color){
			this.color = _color;
		}
		
		public Color getColor(){
			return this.color;
		}
		
	}
	
}
