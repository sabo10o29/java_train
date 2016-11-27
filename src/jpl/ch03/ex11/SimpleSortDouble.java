package jpl.ch03.ex11;

public class SimpleSortDouble extends SortDouble{
    /*static int count = 0;
    double tmp[] =
    { 0.0, 0.1 };
	*/
	
	//doSortの実装
	//
    protected void doSort(){
        for (int i = 0; i < this.getDataLength(); i++){
            for (int j = i + 1; j < this.getDataLength(); j++){
                if (compare(i, j) > 0){		//iが大きい場合に交換を行う（降順）
                    swap(i, j);
                }
            }
        }
        // sortを終えた後に関係ないデータを用いてもう一度ソートを行うと、
        // カウンタが上書きされてしまう
        /*
        if (count == 0){
            count = 1;
            sort(tmp);
        }
        */
    }
}