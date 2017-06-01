package interpret;

import java.util.HashMap;

/**
 * はじめの説明用パネル
 * @author YoshikazuMurase
 *
 */
public class Step1Panel extends StepBasePanel{
	
	Step1Panel(HashMap<String, Object> parameter){
		
		super(parameter);
		
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
		
	}

	@Override
	public boolean nextHandler() {
		return true;
	}

	@Override
	public void clear() {
		
	}
	
}
