package jpl.ch04.ex02;

public class TestSort {
	
	public static void main(String[] args){
		//入力データの作成
		Animal[] testData = new Animal[4];	
		testData[3] = new Animal("Anaconda");
		testData[1] = new Animal("Bambi");
		testData[0] = new Animal("Cat");
		testData[2] = new Animal("Dog");
		
		//ソート用インスタンスの作成
	    SortHarnes bsort = new SimpleSortHarnes();
	   
	    //ソート前のデータ表示
	    System.out.println("ソート前のデータ");
	    for (int i = 0; i < testData.length; i++){
	        System.out.println("\t" + testData[i]);
	    }
	    System.out.println("");
	    
	    //テストデータのソート
	    SortMetrics metrics = bsort.sort(testData);
	    
	    //ソート後のデータ表示
	    System.out.println("ソート後のデータ");
	    for (int i = 0; i < testData.length; i++){
	        System.out.println("\t" + bsort.resultValue()[i]);
	    }
	    System.out.println("Metrics: " + metrics);		//ソート時の検査、比較、交換の回数表示
	    System.out.println("");

	}
}
