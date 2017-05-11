package jpl.ch22.ex01;

public class DoubleArray {

	private Double[] array;
	
	public DoubleArray(Double[] array) {
		this.array = array;
	}
	
	public void showArray(int maxColumn){
		//整数部と少数部の数がmaxColumnとなるように表示する
		//%[インデックス][フラグ][長さ][制度]
		for(Double d : this.array){
			String str = "%1$+."+ maxColumn +"e\n";
			System.out.printf(str, d);
		}
	}
	
	public static void main(String[] args) {
		Double[] test = {
				3.32424242,
				4322423.3232,
				-332.3232,
				-182638.097372
		};
		DoubleArray da = new DoubleArray(test);
		da.showArray(10);
		
	}

}
