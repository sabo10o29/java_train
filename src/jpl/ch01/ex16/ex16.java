package jpl.ch01.ex16;
import java.io.File;
import java.io.IOException;

/***
 * ファイルを読み込んだ際の例外処理を行いその時の値を保持するプログラム
 * @author murase
 *
 */

public class ex16 {
	public static final int MAX_SIZE = 100;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//初期化処理
		double[] data = new double[MAX_SIZE];
		MyUtilities test = new MyUtilities();
		
		
		//ファイルが存在しない場合のテスト
		String data_name = "test2";
		try{
			data = test.getDataSet(data_name);
		}catch(BadDataSetException e){
			System.out.println("Cannnot Open files: " + e.name);
			System.out.println("Error type: " + e.exc.getMessage());
		}
		
		//ファイルを読み込んだ場合のテスト
		data_name = "./src/jpl/ch01/ex16/test";
		try{
			data = test.getDataSet(data_name);
		}catch(BadDataSetException e){
			System.out.println("Cannnot Open files: " + e.name);
			System.out.println("Error type: " + e.exc.getMessage());
		}
	    for(int i=0; i<data.length; i++){
	    	System.out.println(data[i]);
	    }
		
		
	}

}
