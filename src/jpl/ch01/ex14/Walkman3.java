package jpl.ch01.ex14;

public class Walkman3 extends Walkman2{

	Walkman3(int _sirial, String _model) {
		super(_sirial, _model);
	}
	public void communicate(){
		System.out.println("双方向通信ができます。");
	}
	
}
