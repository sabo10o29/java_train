package jpl.ch14.ex01;

public class GetThreadName {

	public static void main(String[] args) {
		Thread thread = Thread.currentThread();
		System.out.println(thread.getName());
	}

}
