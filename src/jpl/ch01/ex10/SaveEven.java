package jpl.ch01.ex10;

/***
 * 値とその値が偶数かどうかをブール値として持つクラス
 * @author murase
 *
 */

public class SaveEven {
	public boolean even;
	public int fib;
	
	SaveEven(int _fib, boolean _even){
		this.fib = _fib;
		this.even = _even;
	}
	
	public void dispFib(){
		if(this.even){
			System.out.println(this.fib+"*");
		}else{
			System.out.println(this.fib+" ");
		}
	}
	
}
