package jpl.ch22.ex04;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

public class Eye implements Observer{

	Target watching;
	
	public Eye(Target target){
		this.watching = target;
		this.watching.addObserver(this);
	}

	@Override
	public void update(Observable o, Object arg) {
		if(o != watching){
			throw new IllegalArgumentException();
		}
		Action action = (Action)arg;
		if(action.getActionName() == "ターゲットに対する属性の削除"){
			System.out.println("ターゲットの属性が削除されました。");
		}
		
		
	}
	
	public static void main(String[] args) {
		//ターゲットの生成
		Target target = new Target();
		//監視者の生成
		Eye observer = new Eye(target);
		
		//ターゲットにたいするアクション→属性の削除が行われた場合のみ通知を受ける
		target.add(new Attr("RED",Color.RED));
		target.add(new Attr("BLUE",Color.BLUE));
		target.add(new Attr("GREEN",Color.GREEN));
		target.find("BLUE");
		target.remove("RED");
		
		
	}

}
