package jpl.ch22.ex04;


/**
 * ターゲットに対するアクションを保持するクラス
 * @author YoshikazuMurase
 *
 */
public class Action {
	
	private String actionname;
	
	public Action(String actionname){
		this.actionname = actionname;
	}
	
	public String getActionName(){
		return actionname;
	}
}
