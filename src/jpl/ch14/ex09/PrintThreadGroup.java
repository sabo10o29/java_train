package jpl.ch14.ex09;

public class PrintThreadGroup implements Runnable{

	private ThreadGroup group;
	
	public PrintThreadGroup(ThreadGroup group) {
		this.group = group;
	}
	
	public static void printThreadGroup(ThreadGroup group){
		PrintThreadGroup thread = new PrintThreadGroup(group);
		new Thread(thread).start();
	}
	
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(2000);
				printThread(this.group);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private void printThread(ThreadGroup group){
		int numThread = group.activeCount();
		int numGroup = group.activeGroupCount();
		
		System.out.println("Group: " + group.getName());
		if(numThread != 0 ){
			
			Thread[] threads = new Thread[numThread];
			group.enumerate(threads, false);
			for(int i = 0; i<threads.length; i++){
				if(threads[i]!=null)
					System.out.println("Thread: " + threads[i].getName());
			}
		}
		
		if( numGroup != 0){
			ThreadGroup[] groups = new ThreadGroup[numGroup];
			group.enumerate(groups, false);
			for(int i = 0; i<groups.length; i++){
				if(groups[i] != null)
					printThread(groups[i]);
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		ThreadGroup parent = new ThreadGroup("Parent");
		
		ThreadGroup child1 = new ThreadGroup(parent,"Child1");
		ThreadGroup child2 = new ThreadGroup(parent,"Child2");
		ThreadGroup child3 = new ThreadGroup(parent,"Child3");
		
		ThreadGroup grandson1 = new ThreadGroup(child1,"Grandson1");
		ThreadGroup grandson2 = new ThreadGroup(child1,"Grandson2");
		
		ThreadGroup[] array = {
				parent, child1, child2, child3, grandson1, grandson2
		};
		
		new Thread(child3, new Hoge(), "Hoge").start();
		PrintThreadGroup.printThreadGroup(array[0]);
	}

	

	

}
