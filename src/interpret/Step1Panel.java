package interpret;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Step1Panel extends StepBasePanel{
	
	Step1Panel(){
		
		titlePanel.setText(TITLE_SPACE+"Interpret へようこそ");
		descriPanel.setText(
				SPACE+"このプログラムでは任意のクラスの\n"
				+ SPACE+"インスタンスを生成します。");
		notifyPanel.setText(
				SPACE+"生成を中止して、実行中のプログラムを終了する\n"
				+ SPACE+"場合には”Cancel”ボタンをクリックしてください。\n"
				+ SPACE+"続行する場合には ”Next” ボタンをクリックして\n"
				+ SPACE+"ください。");
	}

	@Override
	public void init() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void nextHandler() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void clear() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public boolean check() {
		return true;
	}
	
}
