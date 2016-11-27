package jpl.ch03.ex02;

public class Y extends X{

	protected int yMask = 0xff00;
	
	public static void main(String[] args) {
		Y y = new Y();
		
	}
	public Y(){
		System.out.printf("%x  %x  %x \n",this.xMask,this.yMask,this.fullMask);
		fullMask |= yMask;
		System.out.printf("%x  %x  %x \n",this.xMask,this.yMask,this.fullMask);
	}
	

}
