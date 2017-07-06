package jpl.ch22.ex01;


public class DoubleArray {

	private double[] array;

	public DoubleArray(double[] array) {
		this.array = array;
	}

	public void showArray(int maxColumn) {
		// 少数部の数がmaxColumnとなるように表示する
		// %[インデックス][フラグ][長さ][制度]
		int i = 1;
		if (maxColumn > 73) {
			i = 73;
		} else if (maxColumn <= 8) {
			i = 1;
		} else {
			i = maxColumn;
		}

		for (double d : this.array) {
			String str = "%1$+." + i + "e\n";
			System.out.printf(str, d);
		}
	}

	public static void main(String[] args) {
		double[] test = { 3.32424242, 4322423.3232, -332.3232, -182638.097372 };
		DoubleArray da = new DoubleArray(test);
		da.showArray(10);

	}

}
