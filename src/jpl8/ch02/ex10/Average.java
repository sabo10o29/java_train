package jpl8.ch02.ex10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * reduceメソッドは終端操作であり、その後にcountメソッドを実施することができないため
 * 並列ストリームには対応していません
 * @author YoshikazuMurase
 *
 */
public class Average {

	public static Double averageDouble(Stream<Double> stream) {

		AtomicInteger count = new AtomicInteger(0);
		
		return stream.reduce((double) 0, (x, y) -> {
			x = (x*count.get() + y)/(count.get()+1);
			count.incrementAndGet();
			return x;
		});

	}

	public static void main(String[] args) {

		Double[] test = {
			2.0, 4.0, 6.0, 8.0	
		};
		Double result = Average.averageDouble(Arrays.stream(test));
		System.out.println("平均＝"+result);

	}

}
