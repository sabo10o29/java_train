package jpl.ch17.ex03;

import java.io.File;
import java.lang.ref.WeakReference;

public class ResourceImpl implements Resource{
	
//	int keyHash;
	WeakReference<Object> key;
//	Object key;
	boolean needsRelease = false;
	
	//コンストラクタ：キーに対するハッシュコードを生成し。keyHashに格納する
	public ResourceImpl(Object key) {
		this.key = new WeakReference<Object>(key);
//		keyHash = System.identityHashCode(key);
		
		//リソースの設定
		
		needsRelease = true;
	}
	
	//キーの使用：キーをし使用して何かしらリソースを操作する
	public void use(Object key, Object... args){
//		if(System.identityHashCode(key) != keyHash)
//			throw new IllegalArgumentException("wrong key");
		if(!this.key.equals(key))
			throw new IllegalArgumentException("wrong key");
		
		//リソースの使用
	}
	
	//リソースの解放を行う
	public synchronized void release(){
		if(needsRelease){
			needsRelease = false;
			
			//リソースの解放
			System.gc();
			
		}
	}
	
}	
