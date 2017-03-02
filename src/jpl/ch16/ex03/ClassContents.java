package jpl.ch16.ex03;

import java.lang.reflect.Member;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class ClassContents {

	public static void main(String[] args) {
		Class<?> c;
		try {
			c = Class.forName(args[0]);
//			printMembers(c.getFields());
//			printMembers(c.getConstructors());
//			printMembers(c.getMethods());
			printAllMembers(c);
		} catch (ClassNotFoundException e) {
			System.out.println("unknown class: " + args[0]);
		}
		
	}
	
	private static void printAllMembers(Class<?> cls){
		
		System.out.println(cls);
		
		printMembers(cls.getFields(),cls);
		printMembers(cls.getConstructors(),cls);
		printMembers(cls.getMethods(),cls);
		
		Type type = cls.getGenericSuperclass();
		if(type!=null){
			Class<?> scls = null;
			if(type instanceof Class<?>){
				scls = (Class<?>) type;
				printAllMembers(scls);
			}
			else if (type instanceof ParameterizedType){
				scls = (Class<?>) ((ParameterizedType)type).getRawType();
				printAllMembers(scls);
			}else
				throw new Error("Unexpected non-class type");
		}
		
	}
	
	private static void printMembers (Member[] mems, Class<?> cls){
		for(java.lang.reflect.Member m : mems){
//			if (m.getDeclaringClass() == Object.class)
//				continue;
			String decl = m.toString();
			if(decl.matches(".*" + cls.getName() + ".*")){
				System.out.print(" ");
				System.out.println(strip(decl, "java.lang."));
			}else{
			}
		}
	}
	
	private static String strip(String str, String remove){
		return str.replaceAll(remove, "");
	}

}
