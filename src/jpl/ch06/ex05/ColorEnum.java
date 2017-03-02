package jpl.ch06.ex05;

import java.awt.Color;

abstract class ColorEnum {

	enum Colorenum {

		BLUE {
			Color getColor() {
				return Color.blue;
			}
		},
		YELLOW {
			Color getColor() {
				return Color.yellow;
			}
		},
		RED {
			Color getColor() {
				return Color.red;
			}
		};

		abstract Color getColor();

	}

}
