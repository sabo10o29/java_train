package jpl.ch20.ex01;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class Trans {
	
	//インプットストリームから変換マップを通してアウトプットストリームを生成するメソッド
	public static OutputStream translateByte(InputStream in, OutputStream out, String[] map) throws IOException{
		
		byte from = (byte) map[0].charAt(0);
		byte to = (byte) map[1].charAt(0);

		int b;
		while((b = in.read()) != -1){
			out.write(b == from ? to : b);
		}	
		return out;
	}
	
	public static void main(String[] args) {
		InputStream in = System.in;
		OutputStream out = System.out;
		try {
			Trans.translateByte(in, out, args);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

}
