package jpl.ch03.ex11;

public abstract class SortDouble
{
    private double[] values;
    private final SortMetrics curMetrics = new SortMetrics();	//ソートする際の交換・比較・検査回数を記録

    /** 全ソートをするために呼び出される */
    public final SortMetrics sort(double[] data){
        //values = data;			//ソートするデータの格納(従来)
        ////////ch03.ex11////////
    	values = new double[data.length];
    	for(int i=0; i<data.length; i++){
        	values[i] = data[i];
        }
        /////////////////////////
        curMetrics.init();		//curMetricsの初期化
        doSort();				//ソート処理の実行
        return getMetrics();
    }
    
    ///////ch03.ex11//////
    //入力データを直接変更しないようにしたためゲッターを作成
    public double[] resultValue(){
    	return this.values.clone();
    	//return this.values;
    }
    //////////////////////
    
    public final SortMetrics getMetrics(){
        return curMetrics.clone();		//取得先で値が変更されないようにするために複製を返す
    }

    /** 拡張したクラスが要素の数を知るため */
    protected final int getDataLength(){
        return values.length;
    }

    /** 拡張したクラスが要素を調べるため */
    protected final double probe(int i){
        curMetrics.probeCnt++;
        return values[i];
    }

    /** 拡張したクラスが要素を比較するため */
    protected final int compare(int i, int j){
        curMetrics.compareCnt++;
        double d1 = values[i];
        double d2 = values[j];
        if (d1 == d2){
            return 0;					//値が等しい場合には0を返す
        }
        else{
            return (d1 < d2 ? -1 : 1);	//大小関係の結果を返す
        }
    }

    /** 拡張したクラスが要素を交換するため */
    protected final void swap(int i, int j){
        curMetrics.swapCnt++;
        double tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }

    /** 拡張したクラスが実装する -- ソートするのに使用される */
    protected abstract void doSort();

}