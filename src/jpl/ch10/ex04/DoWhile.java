package jpl.ch10.ex04;

public class DoWhile {

	public static void main(String[] args) {
		System.out.println("１～10の二乗を生成するプログラム");
		int i=0;
		while(i<11){
			System.out.println(i+"の２乗"+Math.pow(i, 2));
			i++;
		}
		
		i=0;
		do{
			System.out.println(i+"の２乗"+Math.pow(i, 2));
			i++;
		}while(i<11);
	}

}
