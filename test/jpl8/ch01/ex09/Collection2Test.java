package jpl8.ch01.ex09;


import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;
import StdCap.StdoutCapture;
import jpl8.ch01.ex09.CollectionFilter.Collection2;

public class Collection2Test {

	@Test
	public void test() {
		class Collection2Impl<T> extends ArrayList<T> implements Collection2<T> {

		}
		String[] strs = { "Honda", "Yamaha", "Kawasaki", "BMW", "Suzuki" };
		Collection2<String> list = new Collection2Impl<>();
		Collections.addAll(list, strs);

		String[] expected = new String[] { "BMW" };

		StdoutCapture sc = new StdoutCapture();
		sc.start();

		list.forEachIf(item -> System.out.println(item), (item) -> {
			if (item.length() < 4)
				return true;
			return false;
		});

		sc.stop();
		sc.assertEquals(expected);

	}

}
