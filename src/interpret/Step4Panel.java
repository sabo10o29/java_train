package interpret;

import java.lang.reflect.Constructor;
import java.lang.reflect.Type;

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
		Type[] typee = c.getGenericParameterTypes();
		setParameter(ParameterConst.CLASS_NAME, clazz);
		setParameter(ParameterConst.CONST_NAME, c);
		setParameter(ParameterConst.CONST_PARAMETERS, typee);
		titlePanel.setText(TITLE_SPACE + "引数の設定");
		descriPanel.setText(SPACE + c.toGenericString() +"\n"
							+ SPACE + "のコンストラクタの引数を設定してください\n\n"
							+ SPACE + "基本型の場合は値を入力した後 ”Set” を押して下さい。\n"
							+ SPACE	+ "配列の場合は要素数を入力した後 ”Edit” を押して下さい。\n"
							+ SPACE + "参照型の場合は ”Edit” を押して引数の設定を行います。\n"
							+ SPACE	+ "＊標準コンストラクタしか存在しない場合には ”Set” を押して下さい。\n"
							);
		
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

	@Override
	public boolean check() {
		//標準コンストラクタの場合：lengthが0でargobjがnullになっているか
		//引数ありコンストラクタの場合：lengthが0ではなくargpbjがnullではないか、その中もnullではないかチェック
//		Type[] types = (Type[]) getParameter(ParameterConst.CONST_PARAMETERS);
//		if(types.length==0&&this.argobj==null){
//			return true;
//		}else if(types.length!=0&&this.argobj!=null){
//			for(Object o : argobj){
//				if(o==null)return false;
//			}
//			return true;
//		}else{
//			return false;
//		}
		return true;
	}
	
	
}
