package jpl.ch20.ex08;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import jpl.ch01.ex09.SysoutFibonacci;

//修正：対象の行の次の行が表示される
public class ReadFile {
	
	int[] map = null;

	public static void main(String[] args) {
		
		int count = 0;	//%%カウント用
		
		String current = new File(".").getAbsoluteFile().getParent();
		String outdir = current + "/src/jpl/ch20/ex08/input.txt";
		RandomAccessFile file = null;
		try {
			file = new RandomAccessFile(outdir, "r");
		} catch (FileNotFoundException e) {
			System.out.println("ファイルを読み込むことができませんでした。");
			e.printStackTrace();
		}
		
		//%%の検索&ポイントの保存
		System.out.println("エントリを検索します。");
		long[] points = new long[10];
		String str;
		try {
			// ファイルの検索
			while ((str = file.readLine()) != null) {
				if (str.indexOf("%%") != -1) {
					points[count] = file.getFilePointer();
					System.out.println("Find %% at " + points[count]);
					count++;
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//ランダムにエントリを表示
		System.out.println("ランダムにエントリを表示します。");
		int loopcount = 0;
		while(loopcount != 10){
			int rand = (int)(Math.random()*10);
			if(points[rand] != 0){
				try {
					file.seek(points[rand]);
					System.out.println(file.readLine());
				} catch (IOException e) {
					System.out.println("指定したポイントは存在しません。");
					e.printStackTrace();
				}
			}
			
			loopcount++;
		}
//		
//		System.out.println("test");
//		for(long a : points){
//			try {
//				file.seek(a);
//				System.out.println(file.readLine());
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//		}
		
		
		
		
		
		
		
		
	}

}
