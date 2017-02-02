package jpl.ch13.ex04;

import java.util.ArrayList;

public class CreateClass {
	
	public static ArrayList<Object> getClassList(String _str){
		char splitchar = ' ';
		ArrayList<Object> list = new ArrayList<Object>();
		String[] str = _str.split("\n");
		
		int i=0;
		while(i<str.length){
			//スペースが入っているかチェック
			if(str[i].indexOf(splitchar)!=-1){
				String[] split = str[i].split(String.valueOf(splitchar));
				//区切られた配列の要素が２かチェック
				if(split.length==2){
					Object obj = (Object)string2object(split[0],split[1]);
					if(obj!=null)list.add(obj);
				}
				
			}
			i++;
		}
		
		return list;
	}
	
	public static Object string2object(String classname, String value){
		if(classname==null||value==null){
			return null;
		}else
		switch(classname){
		case "Integer":
			return Integer.valueOf(value);
		case "String":
			return value;
		case "Boolean":
			return Boolean.valueOf(value);
		case "Character":
			return Character.valueOf(value.charAt(0));
		case "Byte":
			return Byte.valueOf(value);
		case "Short":
			return Short.valueOf(value);
		case "Long":
			return Long.valueOf(value);
		case "Float":
			return Float.valueOf(value);
		case "Double":
			return Double.valueOf(value);
		default :
			return null;
		}
	}
	
	public static void main(String[] args){
		String str = "  \nTest NNNkakikukeko\nString aaansasisuseso\nDouble 1222";
		ArrayList<Object> test = CreateClass.getClassList(str);
		for(Object obj:test){
			System.out.println(obj.toString());
		}
	}
	
	
}
