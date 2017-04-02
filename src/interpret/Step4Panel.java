package interpret;

import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import com.sun.glass.ui.CommonDialogs.Type;

public class Step4Panel extends StepBasePanel{
	
	ArgListPanel panel;		//コンストラクタ設定用パネル
	Object[] argobj;		//パラメータ格納用パネル
	
	Step4Panel(){
		
		
	}

	@Override
	public void init() {
		//現在までの設定情報を現在のパラメータに保存
		//保持情報：クラス名、コンストラクタ、引数、引数の値
		Class<?> clazz = (Class<?>) getBfParameter().get(ParameterConst.CLASS_NAME);
		Constructor<?> c = (Constructor<?>) getBfParameter().get(ParameterConst.CONST_NAME);
		java.lang.reflect.Type[] typee = c.getGenericParameterTypes();
		setParameter(ParameterConst.CLASS_NAME, clazz);
		setParameter(ParameterConst.CONST_NAME, c);
		setParameter(ParameterConst.CONST_PARAMETERS, typee);
		titlePanel.setText(TITLE_SPACE + "引数の設定");
		descriPanel.setText(SPACE + "コンストラクタの引数を設定してください\n"
							+ SPACE + c.toGenericString() +"\n\n"
							+ SPACE + "基本型の場合は値、参照型の場合は”Edit”ボタン、\n"
							+ SPACE	+ "配列の場合は要素数を入力した後”Edit”ボタンを押してください。");
		
		//メインパネルの設定
		//標準コンストラクタの場合→メインパネルに通知
		//それ以外→コンストラクタ設定用パネルの配置
		if(typee.length == 0){		//標準コンストラクタを選択した場合
			argobj = null;
			notifyPanel.setText(SPACE + "標準コンストラクタを生成します。\n"
								+ SPACE + "”Next” ボタンを押して下さい。");
		}else{						//それ以外を選択した場合
			argobj = new Object[typee.length];
			panel = new ArgListPanel(typee);
			mainPanel.add(panel);
		}
		
	}

	public void nextHandler(){
		if(panel!=null)argobj = panel.getParam();
		setParameter(ParameterConst.CONST_PARAM_OBJ, argobj);
		System.out.println("インスタンスの生成に必要なパラメータ値を格納しました。");
	}

	@Override
	public void clear() {
		titlePanel.removeAll();
		descriPanel.removeAll();
		mainPanel.removeAll();
		notifyPanel.removeAll();
		notifyPanel.setText("");
	}
	
	
}
