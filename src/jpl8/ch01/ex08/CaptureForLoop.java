package jpl8.ch01.ex08;


import java.util.ArrayList;
import java.util.List;

/**
 * 拡張forループを使用した場合には
 * @author YoshikazuMurase
 *
 */
public class CaptureForLoop {

	public static void main(String[] args) {
		String[] names = { "Peter", "Paul", "Mary" };

		List<Runnable> runnnersA = new ArrayList<>();
		List<Runnable> runnnersB = new ArrayList<>();
		
		//Runnableのリストに２つのほほうで実装
		for (String name : names)
			runnnersA.add(() -> System.out.println(name));
		
		for (int i = 0; i<names.length; i++){
			String str = names[i];
			runnnersB.add(() -> System.out.println(str));
		}
		
		//リスト内のRunnable実装を実行
		System.out.println("拡張forループの実装を実行");
		for(Runnable r : runnnersA){
			new Thread(r).start();
		}
		
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("通常のforループの実装を実行");
		for(Runnable r : runnnersB){
			new Thread(r).start();
		}
		

	}
	
	

}
