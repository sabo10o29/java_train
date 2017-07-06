package jpl.ch23.ex03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Executer {

	private void execCmd(String[] cmd) throws IOException, InterruptedException {

		Process process = Runtime.getRuntime().exec(cmd);

		/* 上記 1 のストリームを別スレッドで出力します。 */
		Runnable inputStreamThread = new Runnable() {
			public void run() {
				try (InputStream in = process.getInputStream();
						BufferedReader br = new BufferedReader(new InputStreamReader(in));) {
					System.out.println("Std print");
					int i = 1;
					String line;
					while ((line = br.readLine()) != null) {
						if (line.indexOf("icmp_seq=5") != -1) {
							process.destroy();
						}
						System.out.println(i + "::" + line);
						i++;

					}
				} catch (Exception e) {
				}
			}
		};

		/* 上記 2 のストリームを別スレッドで出力 */
		Runnable errStreamThread = new Runnable() {
			public void run() {
				try (InputStream ein = process.getErrorStream();
						BufferedReader ebr = new BufferedReader(new InputStreamReader(ein));) {
					System.out.println("Error print");
					int i = 1;
					String line;
					while ((line = ebr.readLine()) != null) {
						if (line.indexOf("icmp_seq=5") != -1) {
							process.destroy();
						}
						System.err.println(i + "::" + line);
						i++;
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {

				}
			}
		};

		Thread std = new Thread(inputStreamThread);
		Thread err = new Thread(errStreamThread);

		/* スレッドを開始します。 */
		std.start();
		err.start();

		/* プロセスが終了していなければ終了するまで待機 */
		process.waitFor();

		/* サブスレッドが終了するのを待機 */
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
