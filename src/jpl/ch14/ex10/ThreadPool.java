/*
 * Copyright (C) 2012, 2013, 2016 RICOH Co., Ltd. All rights reserved.
 */
package jpl.ch14.ex10;

import java.util.LinkedList;

/**
 * Simple Thread Pool class.
 *
 * This class can be used to dispatch an Runnable object to be exectued by a
 * thread.
 *
 * [Instruction] Implement one constructor and three methods. Don't forget to
 * write a Test program to test this class. Pay attention to @throws tags in the
 * javadoc. If needed, you can put "synchronized" keyword to methods. All
 * classes for implementation must be private inside this class. Don't use
 * java.util.concurrent package.
 */
public class ThreadPool {

	/**
	 * Constructs ThreadPool.
	 *
	 * @param queueSize
	 *            the max size of queue
	 * @param numberOfThreads
	 *            the number of threads in this pool.
	 *
	 * @throws IllegalArgumentException
	 *             if either queueSize or numberOfThreads is less than 1
	 */

	private Thread[] threads = null;		
	private int queueSize;
	private LinkedList<Runnable> list = new LinkedList<Runnable>();
	private volatile Boolean start_flug = false;

	public ThreadPool(int queueSize, int numberOfThreads) throws IllegalArgumentException {
		if (queueSize == 0 || numberOfThreads == 0)
			throw new IllegalArgumentException();
		threads = new Thread[numberOfThreads];		//スレッド配列の生成
		this.queueSize = queueSize;					//キューの最大サイズの設定
	}

	/**
	 * Starts threads.
	 *
	 * @throws IllegalStateException
	 *             if threads has been already started.
	 */
	public void start() {	//同時に実行されても必ずどちらかがOKでどちらかがOUTになるようにする。
		synchronized (start_flug) {
			if (start_flug) {
				throw new IllegalStateException();
			} else {
				start_flug = true;
			}
			for (int i = 0; i < threads.length; i++) {
				threads[i] = new Runner(); // スレッドインスタンスの生成
				threads[i].start(); // スレッドスタート
			}
		}
		
	}

	/**
	 * Stop all threads and wait for their terminations.
	 *
	 * @throws IllegalStateException
	 *             if threads has not been started.
	 */
	public void stop() {
		if (!start_flug) {
			throw new IllegalStateException();
		}
		
		//空になるまで実行　＊注意！：ストップ処理が開始していてもディスパッチを受け入れる状態になっている
		while(!list.isEmpty()){
			synchronized (list) {
				System.out.println("すべての処理の完了を待機中");
				list.notifyAll();
			}
		}
		//スレッド終了処理
		start_flug = false;		//スレッド終了フラグをたてる
		for (Thread t : threads) {
			while(t.isAlive()){
				synchronized (list) {
					list.notifyAll();
				}
			}
			System.out.println(t.getName() + "を終了します。");
		}
		System.out.println("スレッドプールを完了します。");

	}

	/**
	 * Executes the specified Runnable object, using a thread in the pool. run()
	 * method will be invoked in the thread. If the queue is full, then this
	 * method invocation will be blocked until the queue is not full.
	 * 
	 * @param runnable
	 *            Runnable object whose run() method will be invoked.
	 *
	 * @throws NullPointerException
	 *             if runnable is null.
	 * @throws IllegalStateException
	 *             if this pool has not been started yet.
	 */
	public synchronized void dispatch(Runnable runnable) {
		// タスクをキューに格納する、格納した場合にはタスクが存在するため、スレッドを起こす
		if (runnable == null)
			throw new NullPointerException();
		if (threads[0] == null)
			throw new IllegalStateException();
		if (start_flug == false)
			throw new IllegalStateException();
		while (true) {
			synchronized (list) {
				if (list.size() <= queueSize) {
					System.out.println("add task " + list.size());
					list.add(runnable);
					list.notifyAll();
					break;
				} 
			}
		}
	}

	// タスク実行用スレッド
	// キューにタスクが入ったらタスクのrunメソッドを実行する
	public class Runner extends Thread {

		private Runnable task = null;

		@Override
		public void run() {
			while (start_flug) {
				//タスク取得処理
				synchronized (list) {
					if (!list.isEmpty()) {		//同期をとってから確認処理
						task = list.poll();
						list.notifyAll();
					} 
				}
				//タスク実行処理
				if(task != null){
					System.out.println("Run task ( Thread: " + this.getName() + ")");
					task.run();
					task = null;
				}
			}
		}
	}

	///スレッドプール外のクラス
	public static void main(String[] args) {
		ThreadPool tp = new ThreadPool(1, 1);
		tp.start();
		CounterTask t = new CounterTask();
		tp.dispatch(t);
		t.waitForRunCount(1);
		tp.stop();

		System.out.println("スレッド数" + Thread.activeCount());

	}

	private static class CounterTask implements Runnable {

		private int runCount = 0;

		@Override
		public synchronized void run() {
			runCount++;
			notifyAll();
		}

		synchronized int waitForRunCount(int count) {
			while (this.runCount < count) {
				try {
					wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return runCount;
		}
	}

}
