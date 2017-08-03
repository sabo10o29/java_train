package java8.ch03.ex07;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 
 * @author YoshikazuMurase
 *
 */
public class MultiComparator {

	public static Comparator<String> getComparator(boolean isNormalOrder, boolean isUppercase, boolean isSpace) {

		return (a, b) -> {

			if (!isUppercase) {
				a = a.toLowerCase();
				b = b.toLowerCase();
			}

			if (isSpace) {
				a = a.replaceAll("\\s", "");
				b = b.replaceAll("\\s", "");
			}
			
			if (isNormalOrder)
				return a.compareTo(b);
			return b.compareTo(a);

		};

	}
	
	public static void main(String[] args) {
		
		String[] bike = {
			"Honda",
			"suzuki",
			"yamaha",
			"Kawasaki",
			"BMW",
			"HARLEY",
			"DUCATI",
			"Triumph",
			"Royal Enfield",
			"Honda_Italy",
			"Honda Motorcycle",
			"HondaManufacturing"
			
		};
		
		System.out.println("正順");
		Arrays.sort(bike, MultiComparator.getComparator(true, false, false));
		Arrays.stream(bike).forEach(System.out::println);
		System.out.println("降順");
		Arrays.sort(bike, MultiComparator.getComparator(false, false, false));
		Arrays.stream(bike).forEach(System.out::println);
		System.out.println("大文字区別：なし");
		Arrays.sort(bike, MultiComparator.getComparator(true, false, false));
		Arrays.stream(bike).forEach(System.out::println);
		System.out.println("大文字区別：あり");
		Arrays.sort(bike, MultiComparator.getComparator(true, true, false));
		Arrays.stream(bike).forEach(System.out::println);
		System.out.println("スペースチェック：なし");
		Arrays.sort(bike, MultiComparator.getComparator(true, false, false));
		Arrays.stream(bike).forEach(System.out::println);
		System.out.println("スペースチェック：あり");
		Arrays.sort(bike, MultiComparator.getComparator(true, false, true));
		Arrays.stream(bike).forEach(System.out::println);
	}

}
