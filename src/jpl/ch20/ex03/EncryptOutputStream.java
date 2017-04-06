package jpl.ch20.ex03;

import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.OutputStream;

//暗号化して書き出すクラス
public class EncryptOutputStream extends FilterOutputStream{

	public EncryptOutputStream(OutputStream out) {
		super(out);
	}
	
	//暗号化：＋１するだけ
	public void write(int ch) throws IOException{
//		System.out.println(ch);
		if(ch != -1){
			ch++;
		}
//		System.out.println(ch);
		super.write((byte)ch);
	}
	
	
	
}
