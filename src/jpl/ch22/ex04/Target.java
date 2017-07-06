package jpl.ch22.ex04;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Observable;

/**
 * 5章のAttributedインタフェースを実装しつつObservableを継承したクラスを作成する
 * それを監視するObserverクラスのサブクラスを作成する
 * ハッシュマップに属性を追加する→追加や削除の際に属性に変更があったことをObserverに通知する
 * 
 * @author YoshikazuMurase
 *
 */
public class Target extends Observable implements Attributed {

	protected Map<String, Attr> attrTable = new HashMap<String, Attr>();

	@Override
	public void add(Attr newAttr) {
		attrTable.put(newAttr.getName(), newAttr);
		setChanged();
		notifyObservers(new Action("ターゲットへの属性追加"));
	}

	@Override
	public Attr find(String attrName) {
		setChanged();
		notifyObservers(new Action("ターゲットに対する属性検索"));
		return attrTable.get(attrName);
	}

	@Override
	public Attr remove(String attrName) {
		setChanged();
		notifyObservers(new Action("ターゲットに対する属性の削除"));
		return attrTable.remove(attrName);
	}

	@Override
	public Iterator<Attr> attrs() {
		setChanged();
		notifyObservers(new Action("属性のいてレータの取得"));
		return attrTable.values().iterator();
	}

}
