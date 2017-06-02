package jpl.ch20.ex10;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

import com.sun.corba.se.impl.encoding.OSFCodeSetRegistry.Entry;

public class CountWord {
	
	private Map<String, Integer> map = new HashMap<String, Integer>();	//単語カウント用マップ
	private String[] keys = new String[10000];
	private int wordcount = 0;

	public CountWord(File file) {
		
		for(int i = 0; i<10000; i++){
			keys[i] = "";
		}
		
		FileReader fr = null;
		StreamTokenizer st = null;
		try {
			fr = new FileReader(file);
			st = new StreamTokenizer(fr);

			int token;
			while((token = st.nextToken()) != StreamTokenizer.TT_EOF){
				switch (token) {
				case StreamTokenizer.TT_EOL:
					System.out.println("<EOL/>");
					break;
				case StreamTokenizer.TT_NUMBER:
					break;
				case StreamTokenizer.TT_WORD:		//文字を検出した場合の処理
					if(wordcount == 0){						//初めて単語を検出した場合の処置
						keys[0] = st.sval;
						map.put(keys[0], 1);
						wordcount++;
					}else{
						boolean newfrag = true;
						for(int i = 0; i<wordcount; i++){	//入力単語が既存の単語と一致するかチェック
							if(st.sval.equals(keys[i])){	//既に単語が存在する場合
								Integer c = map.get(keys[i]);
								map.put(keys[i], c+1);
								newfrag = false;
							}
						}
						if(newfrag==true){					//新しい単語の場合
							keys[wordcount] = st.sval;			
							map.put(keys[wordcount], 1);
							wordcount++;
						}
						
					}
					break;
				default:
					break;
				}
			}
			
			
		} catch (FileNotFoundException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		} catch (IOException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		
	}
	
	public Map<String, Integer> getMap() {
		return map;
	}


	public String[] getKeys() {
		return keys;
	}


	public int getWordcount() {
		return wordcount;
	}
	
	
	public static void main(String[] args) {
		String current = new File(".").getAbsoluteFile().getParent();
		String input = current + "/src/jpl/ch20/ex10/input.txt";
		
		File file = new File(input);
		CountWord test = new CountWord(file);
		System.out.println(test.getWordcount() + "個の単語を検出しました。");
		
//		for(int i=0; i<test.getWordcount(); i++){
//			String key = test.keys[i];
//			System.out.println(key + "::" + test.getMap().get(key));
//		}
		Set<Entry<String, Integer>> entry = test.getMap().entrySet();
		
	}

}
