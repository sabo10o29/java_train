package jpl.ch03.ex06;

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
