package jpl.ch01.ex14;

public class Walkman1 extends Walkman{
	
	Walkman1(int _sirial, String _model) {
		super(_sirial, _model);
		this.iniTerminal();
	}

	public void iniTerminal(){
		this.setTerminal(1);
	}

}
