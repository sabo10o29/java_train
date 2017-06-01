package interpret;

import java.util.HashMap;

public class GrobalDataStore {
	
	/**
	 * 共有パラメータを保存するためのリスト
	 */
	private static final HashMap<String, ShareInstance> list = new HashMap<>();
//	private static final ArrayList<ShareInstance> list = new ArrayList<>();
	private static int num = 0;
	
	/**
	 * 
	 * @return
	 */
	public static int getSize(){
		synchronized (list) {
			return list.size();
		}
	}
	
	/**
	 * 
	 * @return
	 */
	public static boolean isEmpty(){
		synchronized (list) {
			return list.isEmpty();
		}
	}
	
	/**
	 * 
	 * @param i
	 */
	public static void add(String numStr, ShareInstance i){
		synchronized (list) {
			list.put(numStr, i);
			num++;
			System.out.println("新しくインスタンスを追加しました。");
		}
	}
	
	/**
	 * 
	 * @param i
	 */
	public static void replace(String numStr, ShareInstance i){
		synchronized (list) {
			list.put(numStr, i);
			System.out.println("新しくインスタンスを追加しました。");
		}
	}
	
	
	/**
	 * 
	 * @param ind
	 * @return
	 */
	public static ShareInstance getData(String indStr){
		synchronized (list) {
			return list.get(indStr);
		}
	}
	
	public static int getNum(){
		return num;
	}
	
	
}
