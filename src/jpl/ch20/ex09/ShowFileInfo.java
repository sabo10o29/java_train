package jpl.ch20.ex09;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;

public class ShowFileInfo {

	public static void main(String[] args) {
		
		//対象ファイルの読み込み
		String current = new File(".").getAbsoluteFile().getParent();
		String input = current + "/src/jpl/ch20/ex09/input.txt";
		
		File f = new File(input);
		System.out.println("ファイル名:"+f.getName());
		System.out.println("親ファイル名:"+f.getParentFile());
		System.out.println("トータルスペース:"+f.getTotalSpace());
		System.out.println("フリースペース:"+f.getFreeSpace());
		System.out.println("利用可能スペース:"+f.getUsableSpace());
		System.out.println("ファイルが存在するかどうか："+f.exists());
		System.out.println("ファイルを読み込むことができるかどうか。"+f.canRead());
		System.out.println("ファイルの絶対パス："+f.getAbsolutePath());
		Date test = new Date(f.lastModified());
		System.out.println("最終更新日時："+test.toLocaleString());
		
	}

}
