package java8.ch02.ex07;

import java.util.stream.Stream;

/**
 * 
 * 無限ストリームかどうか判断するためには、終端操作が必要
 * しかし、終端操作を行った場合にはストリームは使用できなくなってしまうため、チェックした時点で使い物にならなくなる。
 * このため、無限ストリームをチェックするメソッドは不必要である。
 * 
 * @author YoshikazuMurase
 *
 */
public class CheckStream {

	public static <T> boolean isFinite(Stream<T> stream) {

		return false;

	}

	public static void main(String[] args) {
		Stream<String> streams = Stream.generate(() -> "test");
		isFinite(streams);

	}

}
