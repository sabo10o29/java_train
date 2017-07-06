package jpl.ch21.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * WeakHashMapを使用して返されたデータを保持するプログラムを作成する。 WeakHashMapに今までのデータを名前に紐づけて保存する
 * キーとしてデータを持ち、データの参照が解放された場合にObjectを解放する
 * @author YoshikazuMurase
 *
 */
public class DataHandler {

	// ファイル名とそのデータを保持するマップを作成
//	private WeakHashMap<String, byte[]> Data = new WeakHashMap<String, byte[]>();
	private WeakHashMap<byte[], Object> Data = new WeakHashMap<>();

	byte[] readFile(File file) {

		byte[] data;
		data = Data.get(file.getName());

		// 既にファイルが存在していた場合の処理
		Set set = Data.keySet();
		for (byte[] b : set) {
			return data;
		}

		// 存在しない場合にはファイルの内容を読みこんで、マップに書き込む
		data = readBytesFromFile(file);
		Data.put(file.getName(), data);
		return data;

	}

	// ファイルからバイトデータを読み込む
	public byte[] readBytesFromFile(File file) {
		try {
			byte[] b = new byte[(int) file.length()]; // ファイルの長さの配列を作成
			FileInputStream is = new FileInputStream(file); //
			is.read(b); // ファイルの読み込み
			return b;
		} catch (FileNotFoundException e) {
			System.out.println("対象のファイルが見つかりません。");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("ファイルを読み込むことができませんでした。");
		}

		return null;

	}

	public static void main(String[] args) {

	}

}
