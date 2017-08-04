package java8.ch02.ex07;

import java.util.stream.Stream;

/**
 * 
 * ストリームが有限かどうか判断するためには、終端操作が必要
 * しかし、終端操作を行った場合にはストリームは使用できなくなってしまうため、チェックした時点で使い物にならなくなる。
 * このため、有限かどうかをチェックするメソッドは不必要である。
 * 
 * @author YoshikazuMurase
 *
 */
public class CheckStream {

	public static <T> boolean isFinite(Stream<T> stream) {
		if(stream.count()!=0)
			return true;
		return false;
	}

	public static void main(String[] args) {
		Stream<String> streams = Stream.generate(() -> "test");
		isFinite(streams);

	}

}
