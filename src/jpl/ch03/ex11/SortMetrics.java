package jpl.ch03.ex11;

final class SortMetrics implements Cloneable
{
    public long probeCnt, 	// 単純なデータの値調査
            	compareCnt, // 2つの要素の比較
            	swapCnt; 	// 2つの要素の交換
    
    //初期化処理：インスタンスが生成された後にはじめに実行される
    public void init(){
        probeCnt = swapCnt = compareCnt = 0;
    }
    
    public String toString(){
        return probeCnt + " probes " + compareCnt + " compares " + swapCnt
                + " swaps";
    }

    /** このクラスは、cloneをサポートしている */
    //フィールドはlong型しか保持していないため浅いコピーでOK
    public SortMetrics clone(){
        try{
            return (SortMetrics)super.clone();
        }
        catch (CloneNotSupportedException e){
            throw new InternalError(e.toString());
        }
    }

}
