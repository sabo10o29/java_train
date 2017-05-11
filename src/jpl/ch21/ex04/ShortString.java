package jpl.ch21.ex04;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Scanner;


/**
 * テキストのプログラムのテスト
 * @author YoshikazuMurase
 *
 */
public class ShortString implements Iterator<String>{
	
	private Iterator<String> strings; 	//元の文字列
	private String nextShort;			//次が不明ならばnull
	private final int maxLen;			//この長さ以下の文字列だけを返す
	
	public ShortString(Iterator<String> strings, int maxLen) {
		
		this.maxLen = maxLen;
		this.strings = strings;
		nextShort = null;
		
	}
	
	//次が存在すればtrue
	@Override
	public boolean hasNext() {
		if(nextShort != null){
			return true; 		//すでに見つけている
		}
		while(strings.hasNext()){
			nextShort = strings.next();			//実装しているIteratorの状態が次へ遷移する
			if(nextShort.length() <= maxLen){
				return true;	//最大サイズ以上の場合にはスキップする
								//最大サイズ以下の場合にのみtrueを返す
			}
		}
		nextShort = null;	//見つけることができなかった。
		return false;
	}

	//
	@Override
	public String next() {
		if(nextShort == null && !hasNext()){
			throw new NoSuchElementException();
		}
		String n = nextShort;	//nextShortを記憶する
		nextShort = null;		//nextShortを消費する
		return n;				//nextShortを返す
	}
	
	public void remove(){
		throw new UnsupportedOperationException();
	}
	
	//テキストのサンプルプログラムのテスト
	//３文字以上のものは表示しないイテレータフィルタ
	public static void main(String[] args){

		System.out.println("終了は「end」を入力");
        try(Scanner scan=new Scanner(System.in)){
            while(true){
            	ShortString test = new ShortString(scan, 3);	//テキストプログラムのテスト
            	//hasNextを実行した段階でnextShortに値が保存されている
            	while(test.hasNext()){
        			System.out.println(test.next());	//nextでは次へ遷移せず、値を返すだけ
        		}
                String line=scan.nextLine();
                if("end".equals(line))break;
                System.out.println(line);
            }
        }
		
	}

}
