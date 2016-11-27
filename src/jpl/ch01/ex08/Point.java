package jpl.ch01.ex08;

public class Point {
	public double x, y;
	public void setPoint(int _x,int _y){
		this.x = _x;
		this.y = _y;
	}
	//正答
	public void setPoint(Point point){
		this.x = point.x;
		this.y = point.y;
	}
	
}
