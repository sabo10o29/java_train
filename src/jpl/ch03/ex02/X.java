package jpl.ch03.ex02;

public class X {
	protected int xMask = 0x00ff;
	protected int fullMask;
	
	public X(){
		System.out.println("xMask  yMask  fullMask");
		System.out.printf(" %x    %x       %x \n",this.xMask,0,this.fullMask);
		fullMask = xMask;
		System.out.printf(" %x    %x       %x \n",this.xMask,0,this.fullMask);
	}
	public int mask(int orig){
		return (orig & fullMask);
	}
	
}
