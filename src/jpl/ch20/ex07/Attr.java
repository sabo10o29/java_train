package jpl.ch20.ex07;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringReader;

public class Attr {

	private final String name;					//属性名
	private Object value = null;				//保持する値
	
	public Attr(String name, Object value){
		this.name = name;
		this.value = value;
	}
	public Attr(String name, InputStream in){	//インプットストリームからデータを取得する
		this.name = name;
		
		DataInputStream din = new DataInputStream(in);
		try {
			this.value = din.readUTF();
			System.out.println("フィールドへ値を書き込みました。");
		} catch (IOException e) {
			System.out.println("フィールドへ値を書き込むことができませんでした。");
			e.printStackTrace();
		}
	}
	
	//オブジェクトの内容をアウトプットストリームへ書き込む
	public void writeDataOutputStream(OutputStream out){
		DataOutputStream dout = new DataOutputStream(out);
		
		try {
			dout.writeUTF(this.value.toString());
			System.out.println("アウトプットへ書き込みを行いました。");
		} catch (IOException e) {
			System.out.println("書き込むことができませんでした。");
			e.printStackTrace();
		}
	}
	
	//ゲッターとセッター（セッターはvalueのみ）
	public String getName(){
		return name;
	}
	
	public Object getValue(){
		return value;
	}
	
	public Object setValue(Object newValue){
		Object oldVal = value;
		value = newValue;
		return oldVal;
	}
	
	public String toString(){
		return name + "='" + value + "'";
	}
	
	public static void main(String[] args){
		String current = new File(".").getAbsoluteFile().getParent();
		String outdir = current + "/src/jpl/ch20/ex07/out.txt";
		
		System.out.println("・作成したAttrインスタンスのデータを書き込み");
		Object obj = new String("aiueo");
		Attr attr1 = new Attr("hoge", obj);
		try {
			OutputStream fout = new FileOutputStream(outdir);
			attr1.writeDataOutputStream(fout);
			fout.flush();
			fout.close();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		
		System.out.println("・DataInputStreamから読み込んだインスタンスの生成");
		FileInputStream in;
		try {
			in = new FileInputStream(outdir);
			Attr attr2 = new Attr("hogehoge", in);
			System.out.println("インスタンスフィールド："+attr2.getValue());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
}
