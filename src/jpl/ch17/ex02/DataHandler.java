package jpl.ch17.ex02;

import java.io.File;
import java.io.IOException;
import java.lang.ref.*;
import java.nio.file.Files;

public class DataHandler {
	
//	private File lastFile;						//最後に読んだファイル
	private WeakReference<File> lastFile;		//よわい参照に変更
	private WeakReference<byte[]> lastData;		//（おそらく）最後のデータ
	
	byte[] readFile(File file){
		byte[] data;
		
		//データを記憶しているか調べる
		if(file.equals(lastFile)){
			data = lastData.get();
			if(data != null){
				return data;
			}
		}
		
		//記憶していないので、読み込む
		data = readBytesFromFile(file);
		lastFile = new WeakReference<File>(file);
		lastData = new WeakReference<byte[]>(data);
		return data;
		
	}
	
	public byte[] readBytesFromFile(File file){
		byte[] data = null;
		try {
			data = Files.readAllBytes(file.toPath());
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		return data;
	}
	
	public static void main(String[] args) {

	}
	
	

}
