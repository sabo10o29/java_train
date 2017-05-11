package jpl.ch20.ex04;

import java.io.BufferedReader;
import java.io.FilterReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

public class LineFilterReader extends FilterReader{

	BufferedReader br = null;
	
	public LineFilterReader(Reader in) {
		super(in);
		br = new BufferedReader(in);
	}
	
	public String readLine() throws IOException{
		return br.readLine();
	}

	public static void main(String[] args) {
		StringReader str = new StringReader("testtest \n hogehoge");
		LineFilterReader lrf = new LineFilterReader(str);
		try {
			System.out.println(lrf.readLine());
			System.out.println(lrf.readLine());
			System.out.println(lrf.readLine());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
