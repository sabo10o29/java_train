package jpl.ch01.ex10;

public class SaveEven {
	public boolean even[] = new boolean[20];
	public int i = 0;

	public void checkEven(int x){
		if(x%2==0){
			this.even[this.i] = true;
		}else{
			this.even[this.i] = false;
		}
		this.i++;
	}
	public boolean[] geteven(){
		return this.even;
	}

}
