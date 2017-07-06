package jpl.ch22.ex04;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

public class Inspector implements Observer {

	public Target watching;

	public Inspector(Target target) {
		this.watching = target;
		this.watching.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o != watching) {
			throw new IllegalArgumentException();
		}
		Action action = (Action) arg;
		System.out.println("アクション検知：" + action.getActionName() + "が実行されました。");
	}

	public static void main(String[] args) {
		// ターゲットの生成
		Target target = new Target();
		// 監視者の生成
		Inspector observer = new Inspector(target);

		// ターゲットにたいするアクション→属性の削除が行われた場合のみ通知を受ける
		target.add(new Attr("RED", Color.RED));
		target.add(new Attr("BLUE", Color.BLUE));
		target.find("BLUE");
		target.remove("RED");

	}

}
