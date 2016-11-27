package jpl.ch03.ex07;

import java.awt.Color;

public class ScreenColor {
	private String color;
	
	public ScreenColor(){
		
	}
	public ScreenColor(Object _value){
		this.color = _value.toString();
	}
	public ScreenColor(Color _color){
		this.color = _color.toString();
	}
	
	
	public String toString(){
		return color;
	}
	
}
