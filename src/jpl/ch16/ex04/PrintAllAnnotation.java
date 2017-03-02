package jpl.ch16.ex04;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;

@TypeAnnoTest
public class PrintAllAnnotation {
	
	public static void printAllAnnotation(String str){
		try {
			Class<?> cls = Class.forName(str);
			Annotation[] ano = null;
			
			System.out.println("Annotations: Class");
			ano = cls.getDeclaredAnnotations();
			for(Annotation a : ano){
				System.out.print(" ");
				System.out.println(a);
			}
			
			System.out.println("Annotations: Field");
			Field[] field = cls.getFields();
			for(Field f : field){
				ano = f.getDeclaredAnnotations();
				for(Annotation a : ano){
					System.out.print(" ");
					System.out.println(a);
				}
			}
			
			System.out.println("Annotations: Method");
			Method[] method = cls.getMethods();
			for(Method m : method){
				ano = m.getDeclaredAnnotations();
				for(Annotation a : ano){
					System.out.print(" ");
					System.out.println(a);
				}
			}
			
			System.out.println("Annotations: Constructor");
			Constructor<?>[] constructor = cls.getConstructors();
			for(Constructor<?> c : constructor){
				ano = c.getDeclaredAnnotations();
				for(Annotation a : ano){
					System.out.print(" ");
					System.out.println(a);
				}
			}
			
			System.out.println("Annotations: Package");
			Package pack = cls.getPackage();
			ano = pack.getDeclaredAnnotations();
			for(Annotation a : ano){
				System.out.print(" ");
				System.out.println(a);
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	@FieldAnnoTest
	public int a = 0;
	
	@ConstAnnoTest
	public PrintAllAnnotation() {
	}
	
	@MethodAnnoTest
	public void method1() {
		System.out.println("method1");
	}
	
	public static void main(String[] args) {
		PrintAllAnnotation.printAllAnnotation("jpl.ch16.ex04.PrintAllAnnotation");
	}

}
