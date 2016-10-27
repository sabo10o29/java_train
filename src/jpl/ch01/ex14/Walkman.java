package jpl.ch01.ex14;

abstract class Walkman {
	private int terminal;
	private int sirial;
	private String model;

	Walkman(int _sirial, String _model){
		this.sirial = _sirial;
		this.model = _model;
		this.iniTerminal();

	}
	public abstract void iniTerminal();
	
	public void setTerminal(int i){
		this.terminal = i;
	}

	public int getSirial(){
		return this.sirial;
	}
	public int getTerminal(){
		return this.terminal;
	}
	public String getModel(){
		return this.model;
	}
	public void printSpec(){
		System.out.println("シリアル番号："+this.getSirial()+"モデル："+this.getModel()+"端子数"+this.getTerminal());
		//System.out.println("シリアル番号："+walkman[i].getSirial()+"モデル："+walkman[i].getModel()+"端子数"+walkman[i].getTerminal());
	}

}
