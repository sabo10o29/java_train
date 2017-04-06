package jpl.ch20.ex06;

import java.io.Reader;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

public class Calculater {

	Map<String, Integer> map = new HashMap<String, Integer>();
	String[] strs = null;
	
	public Calculater(String[] strs) {
		this.strs = strs;
		//入力をもとに名前付き変数を設定
		for(String str : strs){
			map.put(str, 0);
		}
	}
	
	//計算式を入力し、mapに格納されている変数に値を設定
	public void calcWithStr(String str){
		//入力が計算式の場合の処理
		StringTokenizer in  = new StringTokenizer(str);
		if(str.equals("\n")){
			//入力がない場合の処理
			showAllValue();
			return;
		}
		try{
			String name = in.nextToken();
			String op = in.nextToken();
			String val = in.nextToken();
//			System.out.println(name + ":" + op + ":" + val);

			if (op.equals("+")) {
				Integer value = map.get(name) + Integer.valueOf(val);
				map.put(name, value);
			} else if (op.equals("-")) {
				Integer value = map.get(name) - Integer.valueOf(val);
				map.put(name, value);
			} else if (op.equals("=")) {
				map.put(name, Integer.valueOf(val));
			} else {
				System.out.println("計算記号が正しくありません。");
			}
		}catch(NoSuchElementException e1){
			System.out.println("そのような名前の変数は存在しません");
		}catch(NumberFormatException e2){
			System.out.println("不正な値です。");
		}
		
	}
	
	//すべての設定値を表示するメソッド
	public void showAllValue(){
		System.out.println("最終的な値は");
		for(String str : this.strs){
			System.out.println(str + "=" + map.get(str));
		}
		System.out.println("です。");
	}
	
	
	
	public static void main(String[] args) {
			String[] strs = {"hoge", "test", "etc"};
			Calculater test = new Calculater(strs);
			test.calcWithStr("hoge + 4");
			test.calcWithStr("test - 4");
			test.calcWithStr("etc = 100");
			test.calcWithStr("");
			test.calcWithStr("test + ?");
			test.calcWithStr("\n");
			
	}

}
