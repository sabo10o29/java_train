package jpl.ch23.ex01;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

public class Pluning {
	
	public static Process userProg(String cmd)throws IOException{
		Process proc = Runtime.getRuntime().exec(cmd);
		plugTogether(System.in, proc.getOutputStream());
		plugTogether(System.out, proc.getInputStream());
		plugTogether(System.err, proc.getErrorStream());
		return proc;
		
	}
	
	
	public static void plugTogether(InputStream in, OutputStream out)throws IOException{
		byte[] buf = new byte[1024];
		while(true){
			int i = in.read(buf);
			if(i<0)break;
			out.write(buf, 0, i);
		}
	}
	
	public static void plugTogether(OutputStream out, InputStream in)throws IOException{
		
	}
	
	public static void plugTogether(PrintStream ps, InputStream in)throws IOException{
	
	}
	
	
}
