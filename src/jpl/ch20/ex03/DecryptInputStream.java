package jpl.ch20.ex03;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class DecryptInputStream extends FilterInputStream{

	protected DecryptInputStream(InputStream in) {
		super(in);
	}

	//オーバーライド：暗号化されたバイトストリームを復元する
	public int read() throws IOException{
		int c = super.read();
		if(c != -1){
			c--;
		}
		return c;
	}
	
}
