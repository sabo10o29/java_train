package jpl.ch09.ex01;

public class CalcInf {

	public static void main(String[] args) {
		Double a = Double.POSITIVE_INFINITY;
		Double b = Double.NEGATIVE_INFINITY;

		System.out.println("+∞ + +∞ ="+ (a+a));
		System.out.println("-∞ + -∞ ="+ (b+b));
		System.out.println("+∞ + -∞ ="+ (a+b));
		System.out.println("+∞ + -∞ ="+ (b+a));
		
		System.out.println("+∞ - +∞ ="+ (a-a));
		System.out.println("-∞ - -∞ ="+ (b-b));
		System.out.println("+∞ - -∞ ="+ (a-b));
		System.out.println("-∞ - +∞ ="+ (b-a));
		
		System.out.println("+∞ * +∞ ="+ (a*a));
		System.out.println("-∞ * -∞ ="+ (b*b));
		System.out.println("+∞ * -∞ ="+ (a*b));
		System.out.println("-∞ * +∞ ="+ (b*a));
		
		System.out.println("+∞ / +∞ ="+ (a/a));
		System.out.println("-∞ / -∞ ="+ (b/b));
		System.out.println("+∞ / -∞ ="+ (a/b));
		System.out.println("-∞ / +∞ ="+ (b/a));
		
		System.out.println("+∞ % +∞ ="+ (a%a));
		System.out.println("-∞ % -∞ ="+ (b%b));
		System.out.println("+∞ % -∞ ="+ (a%b));
		System.out.println("-∞ % +∞ ="+ (b%a));
		
	}

}
