package jpl.ch04.ex01;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Vehicle test = new Vehicle();
		if(test.start()){
			System.out.println("ガソリンorバッテリーがあります");
		}else{
			System.out.println("ガソリンorバッテリーがありません");
		}
	}
}
