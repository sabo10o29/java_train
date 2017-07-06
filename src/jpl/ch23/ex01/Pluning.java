package jpl.ch23.ex01;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;


public class Pluning {

	public static Process userProg(String cmd) throws IOException {
		Process proc = Runtime.getRuntime().exec(cmd);
		plugTogether(System.in, proc.getOutputStream());
		plugTogether(proc.getInputStream(), System.out);
		plugTogether(proc.getErrorStream(), System.err);
		return proc;

	}

	public static void plugTogether(InputStream in, OutputStream out) {
		new Thread(() -> {

			try {
				byte[] buf = new byte[1024];
				int size = -1;
				while (-1 != (size = in.read(buf))) {
					out.write(buf, 0, size);
				}
				out.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}).start();

	}

	public static void main(String[] args) {

		try {
			Pluning.userProg("ping -c 5 localhost");
			Pluning.userProg("ping -c 5 hogehoge");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
