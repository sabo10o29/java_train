package jpl.ch04.ex02;

public class SimpleSortHarnes extends SortHarnes{
	//doSortの実装
    public void doSort(){
        for (int i = 0; i < this.getDataLength(); i++){
            for (int j = i + 1; j < this.getDataLength(); j++){
                if (compare(i, j) > 0){		//iが大きい場合に交換を行う（降順）
                    swap(i, j);
                }
            }
        }
    }
}
