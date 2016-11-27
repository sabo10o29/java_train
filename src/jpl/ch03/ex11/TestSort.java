package jpl.ch03.ex11;

//testDataを書き換えることでSortMetricsのデータも書き換わるため
//あたかも、書き換えたデータのソート結果がSortMetricsに格納されているように見えてしまう。
//→sort()メソッドの引数を直接valueに格納するのではなく値をコピーしたうえでソートを行う
//また、結果もそのまま返しては改竄されてしまうためクローンを作成したものを返すresultValueメソッドを作成する

public class TestSort {
	static double[] testData = { 0.3, 1.3e-2, 7.9, 3.17 };	
	
	    public static void main(String[] args){
	        /*
	    	SortDouble bsort = new SimpleSortDouble();		//ソート用インスタンス
	        SortMetrics metrics = bsort.sort(testData);		//テストデータのソート
	        System.out.println("Metrics: " + metrics);		//ソート時の検査、比較、交換の回数表示
	        
	        for (int i = 0; i < testData.length; i++){
	            System.out.println("\t" + testData[i]);		//参照の値渡しなのでvaluesへの変更が反映
	        }*/
	    	
	    	SortDouble bsort = new SimpleSortDouble();		//ソート用インスタンス
	        SortMetrics metrics = bsort.sort(testData);		//テストデータのソート
	        System.out.println("Metrics: " + metrics);		//ソート時の検査、比較、交換の回数表示
	        
	        for (int i = 0; i < testData.length; i++){
	            System.out.println("\t" + bsort.resultValue()[i]);
	        }
	        System.out.println("");
	        
	        //testDataを書き換えても結果が変更されないことを確認
	        System.out.println("testDataを書き換えても結果が変更されないことを確認");
	        testData[0] = 0;
	        System.out.println("Metrics: " + metrics);
	        for (int i = 0; i < testData.length; i++){
	            System.out.println("\t" + bsort.resultValue()[i]);
	        }
	        System.out.println("");
	        
	        //resultValueの戻り値を書き換えても結果が変更されないことを確認
	        System.out.println("resultValueの戻り値を書き換えても結果が変更されないことを確認");
	        double[] test = bsort.resultValue();
	        test[0] = 0;
	        System.out.println("Metrics: " + metrics);
	        for (int i = 0; i < testData.length; i++){
	            System.out.println("\t" + bsort.resultValue()[i]);
	        }
	        System.out.println("");
	        

	    }
}
