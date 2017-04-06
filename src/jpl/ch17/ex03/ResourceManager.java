package jpl.ch17.ex03;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

//
public class ResourceManager {
	
	final ReferenceQueue<Object> queue;			//参照キュー
	final Map<Reference<?>, Resource> refs;		//
	final Thread reaper;						//刈り取り用スレッド
	boolean shutdown = false;					//
	
	public ResourceManager(){
		queue = new ReferenceQueue<Object>();
		refs = new HashMap<Reference<?>, Resource>();
		reaper = new ReaperThread();
		reaper.start();
		
		//リソースの初期化
	}
	
	public synchronized void shutdown(){
		if(!shutdown){
			shutdown = true;
			reaper.interrupt();
		}
	}
	
	public synchronized Resource getResource(Object key){
		if(shutdown) throw new IllegalStateException();
		
		Resource res = new ResourceImpl(key);	//キー値からリソースの生成
		Reference<?> ref = new PhantomReference<Object>(key, queue);	//新たに参照オブジェクトを作成し、指定された参照キューにキー値で登録を行う
		refs.put(ref, res);		//マップに参照オブジェクトとそのリソースを保存
								//マップに結びつけることでファントム参照オブジェクトを到達可能に維持する
								//リソースとnの結びつけ
		
		return res;
	}
	
	public static void main (String args[]){
		
	}

	//刈り取りスレッド
	public class ReaperThread extends Thread{
		
		public void run(){
			//割り込まれるまで実行
			while(true){
				try{
					Reference<?> ref = queue.remove();		//参照キューに存在する参照＝もう使用されていないインスタンス
					Resource res = null;
					synchronized(ResourceManager.this){
						res = refs.get(ref);				//マップから弱い参照をキーとして取得したリソースの取得
						refs.remove(ref);					//マップからリソースを取り除く＝強い参照ではないためGCできる。
					}
					res.release();							//リソースの削除
					ref.clear();							//弱い参照のクリア
					
				}catch (InterruptedException ex) {
					break; //すべて終了
				}
			}
			
		}
		
	}
	
}

