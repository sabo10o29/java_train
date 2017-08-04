package java8.ch01.ex12;


import java.util.AbstractList;
import java.util.ArrayList;
import java.util.stream.Stream;

/**
 * 親にCollectionが存在しているときに
 * 引数なしのstream()を自分で実装していた場合にはコンパイルエラーとなる
 * @author YoshikazuMurase
 *
 * @param <E>
 */
public class TestList<E> extends ArrayList<E>{
	
	public void stream() {
        System.out.println("Java1.7");
    }
	
	public static void main (String[] args){
		TestList<String> test = new TestList<>();
		test.stream();
	}

}
