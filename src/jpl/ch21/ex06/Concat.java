package jpl.ch21.ex06;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.SequenceInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * 複数のファイルを読み込んで結合したファイルを出力する
 * 何もファイルが存在しない場合は、キーボード入力を出力する
 * 課題：Enumrationを使用して順番にFileInputStreamを参照し、Openし、出力を行い、クローズ
 * 以上の処理を繰り返す
 * @author YoshikazuMurase
 *
 */
public class Concat {
	
	public static void main(String[] args) throws IOException{
		
		InputStream in;	//文字を読み込むべきストリーム
		int ch;
		if(args.length == 0){
			in = System.in;
			while((ch = in.read())!=-1){
				System.out.write(ch);
			}
		}else{	//引数にファイルが指定されている場合
			List<File> filelist = new ArrayList<File>(args.length);		//ファイルクラスのリストを作成
			for(String arg :args){
				filelist.add(new File(arg));							
			}
			Enumeration<File> files = Collections.enumeration(filelist);
			
			while(files.hasMoreElements()){
				ch=0;
				FileInputStream is = new FileInputStream(files.nextElement());
				BufferedInputStream b = new BufferedInputStream(is);
				while((ch=b.read())!=-1){
					System.out.write(ch);
				}
				is.close();
				b.close();
			}
			
		}
	}

}
