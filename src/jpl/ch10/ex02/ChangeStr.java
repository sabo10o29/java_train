package jpl.ch10.ex02;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChangeStr {

	String result;
	
	public static void main(String[] args) {
		
		ChangeStr test = new ChangeStr("test n 765");
	}
	
	ChangeStr(String str){
		
		StringBuilder sb = new StringBuilder();
	    sb.append(str);
	    
	    char[] c = str.toCharArray();
		int num = c.length;
		
		for(int i=0; i<num; i++){
			
			switch(c[i]){
			case 'n':
				sb.insert(i, "\\");
				num++;
				i++;
				c = new String(sb).toCharArray();
				break;
			case 't':
				sb.insert(i, "\\");
				num++;
				i++;
				c = new String(sb).toCharArray();
				break;
			case 'b':
				sb.insert(i, "\\");
				num++;
				i++;
				c = new String(sb).toCharArray();
				break;
			case 'r':
				sb.insert(i, "\\");
				num++;
				i++;
				c = new String(sb).toCharArray();
				break;
			case 'f':
				sb.insert(i, "\\");
				num++;
				i++;
				c = new String(sb).toCharArray();
				break;
			case '\\':
				sb.insert(i, "\\");
				num++;
				i++;
				c = new String(sb).toCharArray();
				break;
			case '\'':
				sb.insert(i, "\\");
				num++;
				i++;
				c = new String(sb).toCharArray();
				break;
			case '"':
				sb.insert(i, "\\");
				num++;
				i++;
				c = new String(sb).toCharArray();
				break;
			default:
				str = new String(sb);
				String regex = "[0-7][0-7][0-7]";
				Pattern p = Pattern.compile(regex);
				Matcher m = p.matcher(str);
				result = m.replaceFirst("\\\\$0");
				break;
			}
			
		}
		System.out.println(result);
		
		
		
		
		
	
		
	}
	
	

}