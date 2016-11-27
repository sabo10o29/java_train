package jpl.ch03.ex12;

abstract class SortHarnes {
	private Object[] values;
    private final SortMetrics curMetrics = new SortMetrics();	//ソートする際の交換・比較・検査回数を記録

    /** 全ソートをするために呼び出される */
    public final SortMetrics sort(Object[] data){
        ////////ch03.ex12////////
    	values = new Object[data.length];
    	for(int i=0; i<data.length; i++){
        	values[i] = data[i];
        }
        /////////////////////////
        curMetrics.init();		//curMetricsの初期化
        doSort();				//ソート処理の実行
        return getMetrics();
    }
    
    ///////ch03.ex12//////
    public Object[] resultValue(){
    	return this.values;
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
    protected final Object probe(int i){
        curMetrics.probeCnt++;
        return values[i];
    }

    /** 拡張したクラスが要素を比較するため */
    //オブジェクトクラスの比較にはtoStringで取得した文字列大小関係を用いる
    protected final int compare(int i, int j){
        curMetrics.compareCnt++;
        Object d1 = values[i];
        Object d2 = values[j];
        //toStringの文字列を比較する（等値：0、d1>d2：＋、d1<d2：-）
        return d1.toString().compareTo(d2.toString());
        
    }

    /** 拡張したクラスが要素を交換するため */
    protected final void swap(int i, int j){
        curMetrics.swapCnt++;
        Object tmp = values[i];
        values[i] = values[j];
        values[j] = tmp;
    }

    /** 拡張したクラスが実装する -- ソートするのに使用される */
    protected abstract void doSort();
}
