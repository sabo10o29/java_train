package java8.ch01.ex09;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * リストの中で条件にあうものだけを対象にして操作を行いたい場合に使用できる
 * 
 * @author YoshikazuMurase
 *
 */
public class CollectionFilter {

	public static void main(String[] args) {

		class Collection2Impl<T> extends ArrayList<T> implements Collection2<T> {

		}
		String[] strs = { "Honda", "Yamaha", "Kawasaki", "BMW", "Suzuki" };
		Collection2<String> list = new Collection2Impl<>();

		Collections.addAll(list, strs);
		System.out.println("forEachの出力");
		list.forEach(item -> System.out.println(item));
		System.out.println("forEachIfの出力");
		list.forEachIf(item -> System.out.println(item), (item) -> {
			if (item.length() < 4)
				return true;
			return false;
		});

	}

	/**
	 * Collection2の実装
	 * 
	 * @author YoshikazuMurase
	 *
	 * @param <T>
	 */

	/**
	 * アイテムをフィルターして、trueの場合のものだけを出力するメソッドの実装
	 * 
	 * @author YoshikazuMurase
	 * @param <T>
	 */
	public interface Collection2<T> extends Collection<T> {

		public default void forEachIf(Consumer<T> action, Predicate<T> filter) {
			// 入力に対してフィルタを行いtrueのものだけを実行するメソッド
			forEach((ai) -> {
				if (filter.test(ai))
					action.accept(ai);
			});
		}

	}
}
