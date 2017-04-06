package jpl.ch17.ex01;

import java.util.HashMap;
import java.util.Map;

//メモリ使用量がかわらない
public class TransitionMemory{

	public static void main(String[] args) {
//		TransitionMemory t = new TransitionMemory();
//		new Thread(t).start();
		
		// 起動時のメモリの表示
		System.out.println("起動直後：" + Runtime.getRuntime().freeMemory());
		Map<Integer, Object> map = new HashMap<Integer, Object>();

		for (int i = 0; i < 100000; i++) {
			map.put(i, new Object());
		}

		System.out.println("インスタンスを大量に生成した後：" + Runtime.getRuntime().freeMemory());
		
//		for (int i = 0; i < 100000; i++) {
//			map.remove(i);
//		}
		map.clear();
		map=null;

		System.out.println("インスタンスを解放した直後：" + Runtime.getRuntime().freeMemory());

		System.gc();
		
		System.out.println("GC実施後：" + Runtime.getRuntime().freeMemory());
		
	}

}
