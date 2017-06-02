package jpl.ch20.ex03;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilterInputStream;
import java.io.FilterOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;

public class Encryption {

	
	//暗号化・複合化は単体とセットで実施する必要がある
	public static void main(String[] args) {
		
		String current = new File(".").getAbsoluteFile().getParent();
		String input = current + "/src/jpl/ch20/ex03/input.txt";
		String encrypt = current + "/src/jpl/ch20/ex03/encrypt.txt";
		
		try (FileInputStream in = new FileInputStream(input);
				FileOutputStream out = new FileOutputStream(encrypt)) {
			
			FilterOutputStream filout = new EncryptOutputStream(out);
			int data;
			while ((data = in.read()) != -1) {
				filout.write(data);
				System.out.write(data);
			}
			System.out.println();
			System.out.println("暗号化して出力しました。");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println("暗号化した文字列を復元します。");
		
		try (FileInputStream in = new FileInputStream(encrypt);) {
			
			DecryptInputStream dec = new DecryptInputStream(in);
			
			int data;
			while ((data = dec.read()) != -1) {
				System.out.write(data);
			}
			System.out.println();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
