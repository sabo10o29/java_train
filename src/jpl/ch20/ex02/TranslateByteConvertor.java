package jpl.ch20.ex02;

import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class TranslateByteConvertor extends FilterReader{
	
	private int from;
	private int to;
	
	protected TranslateByteConvertor(Reader in, String[] map) {
		super(in);
		this.from = (int) map[0].charAt(0);
		this.to = (int) map[1].charAt(0);
//		System.out.println(this.from);
//		System.out.println(this.to);
	}
	
	//オーバーライド：変換マップをもとに読み込んだ文字を変換する
	public int read() throws IOException{
		int c = super.read();
		if(c == from) c = to;
//		System.out.println(c);
		return c;
	}
	
	public static void main(String[] arg){
		String[] map = {"b","B"};
		StringReader src = new StringReader("abrakatabra!");
		FilterReader f = new TranslateByteConvertor(src, map );
		int c;
		try {
			while((c = f.read()) != -1){
				System.out.print((char)c);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
}
