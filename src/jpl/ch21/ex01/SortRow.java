package jpl.ch21.ex01;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import jpl.ch20.ex04.LineFilterReader;

/**
 * ファイルの各行を読み込みソートして表示する
 * @author YoshikazuMurase
 *
 */
public class SortRow {
	
	public static List<String> SortFile(String filename){
		List<String> list = new ArrayList<>();
		try {
			FileReader fr = new FileReader(filename);
			LineFilterReader lfr = new LineFilterReader(fr);
			
			String target;
			while((target = lfr.readLine()) != null){
				int ind = 0;
				for(int i = 0; i<list.size(); i++){
					String comp = list.get(i);
					if(target.compareTo(comp)<0){
						
					}else{
						ind++;
					}
				}
				list.add(ind, target);	
			}
			
		} catch (IOException e) {
			System.out.println("ファイルを読み込むことができませんでした。");
		}
		
		
		return list;
	}
	
	
	
	public static void main(String[] args) {
		
		//ファイルの指定
		String current = new File(".").getAbsoluteFile().getParent();
		String input = current + "/src/jpl/ch21/ex01/input.txt";
		
		//ファイルのソート
		List<String> list = SortRow.SortFile(input);
		
		//ソートした文字の表示
		for(int i = 0; i<list.size(); i++){
			System.out.println(i+"::"+list.get(i));
		}
		
	}

}
