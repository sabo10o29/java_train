package jpl.ch14.ex02;

public class PrintServer implements Runnable{

	private static final String name = "Own";
	private final PrintQueue requests = new PrintQueue();
	
	
	public PrintServer(){
		new Thread(this,name).start();
	}
	public void print (PrintJob job){
		requests.add(job);
	}
	
	@Override
	public void run() {
		if(name.equals(Thread.currentThread().getName())){
			System.out.println("Runメソッドを実行中");
			for(;;){
				realPrint(requests.remove());
			}
		}else{
			System.out.println("スレッド名：　" + Thread.currentThread().getName());
			System.out.println("スレッドが異なるためRunメソッドを終了します。");
			
		}
	}
	private void realPrint(PrintJob job){
		//印刷処理
	}
	
	public static void main (String[] args){
		PrintServer test = new PrintServer();
		new Thread(test).start();
	}

}
