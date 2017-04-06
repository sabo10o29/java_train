package jpl.ch20.ex05;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

public class SearchWord {

	public static void main(String[] args) {
		
		//検索対象テキストの読み込み
		String current = new File(".").getAbsoluteFile().getParent();	//
		String input = current + "/src/jpl/ch20/ex05/input.txt";		//検索対象のテキスト
		FileReader file = null;
		LineNumberReader in = null;
		try {
			file = new FileReader(input);
			in = new LineNumberReader(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("ファイルが存在しません！");
		}

		//検索ワードの取得
		System.out.println("文字を入力して下さい。");
		Scanner scan = new Scanner(System.in);
	    String str = scan.next();
	    System.out.println(str + "の単語を検索します。");
	    
	    
	    //ワードの検索
	    
	    String strline;
	    try {
			while( (strline = in.readLine()) != null){
				if(strline.indexOf(str) != -1){
					System.out.println(in.getLineNumber()+"::"+strline);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    

	}

}
