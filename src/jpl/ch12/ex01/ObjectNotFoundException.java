package jpl.ch12.ex01;

public class ObjectNotFoundException extends Exception{
	public final String name;
	
	public ObjectNotFoundException(String name){
		super("The Object is not found: " + name);
		this.name = name;
	}
}
