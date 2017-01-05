package jpl.ch10.ex01;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeStr {

	
	public static void main(String[] args) {
		
		ChangeStr test = new ChangeStr("test n 765");
		
	}
	
	ChangeStr(String str){
		
		StringBuilder sb = new StringBuilder();
	    sb.append(str);
	    
	    char[] c = str.toCharArray();
		int num = c.length;
		for(int i=0; i<num; i++){
			if(c[i]=='n'||c[i]=='t'||c[i]=='b'||c[i]=='r'||c[i]=='f'||c[i]=='\\'||c[i]=='\''||c[i]=='"'||c[i]=='t'){
				sb.insert(i, "\\");
				num++;
				i++;
				c = new String(sb).toCharArray();
			}
			
		}
		
		str = new String(sb);
		String regex = "[0-7][0-7][0-7]";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(str);
		String result = m.replaceFirst("\\\\$0");
	    
	    System.out.println(result);
	}
	
	

}
