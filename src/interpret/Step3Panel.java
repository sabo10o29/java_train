package interpret;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.lang.reflect.Constructor;

import javax.swing.JComboBox;

import com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const;

public class Step3Panel extends StepBasePanel{
	
	//前のステップでクラスを選択していない場合にはこのステップにいけないようにする
	//コンストラクタが実行される時にクラスは指定されていないためエラーが発生する
	Step3Panel(){
		titlePanel.setText(TITLE_SPACE+"表示されないタイトル");
		descriPanel.setText(SPACE + "この画面が表示される場合にはプログラムのバグです。");
		descriPanel.setPreferredSize(new Dimension(10, 20));
		notifyPanel.setPreferredSize(new Dimension(10, 50));
		
	}

	@Override
	public void init() {
		//現在までの設定情報を現在のパラメータに保存
		//保持情報：クラス名、コンストラクタ
		Class<?> clazz = (Class<?>)getBfParameter().get(ParameterConst.CLASS_NAME);
		setParameter(ParameterConst.CLASS_NAME, clazz);
		titlePanel.setText(TITLE_SPACE+"コンストラクタを選択してください");
		descriPanel.setText(SPACE + clazz.getSimpleName() + "のコンストラクタを選択します。\n"
							+ SPACE + "以下のドロップダウンリストからコンストラクタを選択してください。");
		descriPanel.setPreferredSize(new Dimension(10, 20));
		notifyPanel.setPreferredSize(new Dimension(10, 50));
		
		setConstList(clazz);
	}
	
	//コンストラクタの取得
	//リストをドロップダウンリストで表示
	public void setConstList(Class<?> clazz){
		Constructor<?>[] consts = clazz.getConstructors();
		String[] str = new String[consts.length];
		if(consts != null){
			if(consts.length == 0) {
				notifyPanel.setText("\n"+SPACE + "コンストラクタが存在しません。 \n"
						+ SPACE + " クラスを選択しなおしてください。");
				return;
			}
			
			//コンボボックスの作成（コンストラクタ一覧をコンボボックスに追加）
			for(int i = 0; i<consts.length; i++){
				str[i] = consts[i].toGenericString();
			}
			JComboBox<String> combo = new JComboBox<String>(str);
			combo.setPreferredSize(new Dimension(350, 25));
			setParameter(ParameterConst.CONST_NAME, consts[0]);	//コンストラクタの初期値
			combo.addItemListener(new ItemListener() {			//コンストラクタを選択したときの処理
				@Override
				public void itemStateChanged(ItemEvent e) {
					//コンストラクタを選択した際の処理
					//パラメータにコンストラクタを格納
					Constructor<?> c = getConst(consts, e.getItem().toString());
					setParameter(ParameterConst.CONST_NAME, c);
				}
			});
			
			mainPanel.add(combo);
			
			notifyPanel.setText("\n\n"+SPACE + "選択したコンストラクタで良けば \n"
								+ SPACE + " ”Next” ボタンをクリックしてください。");
			
		}
	}
	
	//コンンストラクタ名からコンストラクタを返すメソッド
	public Constructor<?> getConst(Constructor<?>[] consts, String target){
		for(Constructor<?> c : consts){
			if(target.equals(c.toGenericString())){
				return c;
			}
		}
		return null;
	}

	@Override
	public void nextHandler() {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void clear() {
		titlePanel.removeAll();
		descriPanel.removeAll();
		mainPanel.removeAll();
		notifyPanel.removeAll();
	}

}
