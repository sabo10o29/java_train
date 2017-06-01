package jpl.ch23.ex02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Executer {
	
	InputStream in = null;
	InputStreamReader ir = null;
	BufferedReader br = null;
	
	InputStream ein = null;
	InputStreamReader eis = null;
	BufferedReader ebr = null;

	private void execCmd(String[] cmd) throws IOException, InterruptedException {

		Process process = Runtime.getRuntime().exec(cmd);
		
		//標準出力用スレッド
		Runnable inputStreamThread = new Runnable() {
			public void run() {
				try {
					in = process.getInputStream();
					ir = new InputStreamReader(in);
					br = new BufferedReader(ir);
					System.out.println("Std print");
					int i = 1;
					String line;
					while ((line = br.readLine()) != null) {
						if(line.indexOf("icmp_seq=5")!=-1){
							System.out.println("Find specify str");
							break;
						}
						System.out.println(i + "::" + line);
						i++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					System.out.println("Std：終了処理の入りました。");
					try {
						in.close();
						ir.close();
						br.close();
						process.destroy();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
			}
		};
		
		//エラー出力時のスレッド
		Runnable errStreamThread = new Runnable() {
			public void run() {
				try {
					ein = process.getErrorStream();
					eis = new InputStreamReader(ein);
					ebr = new BufferedReader(eis);
					System.out.println("Error print");
					int i = 1;
					String line;
					while ((line = ebr.readLine()) != null) {
						if(line.indexOf("icmp_seq=5")!=-1){
							System.out.println("Find specify str");
							break;
						}
						System.err.println(i + "::" + line);
						i++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally{
					System.out.println("Err：終了処理に入りました。");
					try {
						ein.close();
						eis.close();
						ebr.close();
						process.destroy();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
				}
			}
		};

		
		//ここまで初期設定

		Thread std = new Thread(inputStreamThread);
		Thread err = new Thread(errStreamThread);

		std.start();
		err.start();

		std.join();
		err.join();

	}

	public static void main(String[] args) {

		Executer SampleRunTime = new Executer();
		try {
			SampleRunTime.execCmd(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
