package interpret;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//エラーはポップアップで表示
//課題：コンボボックスで何も選択していない場合に最初のコンストラクタを選択するようにする
//課題：エラーが発生した場合に何のエラーなのかを表示する
//課題：配列の数が多い場合にスクロールばーで表示する
//課題：サブダイアログパネルにタイトル・説明・通知パネルを設定する
//課題：配列型でも表示・値の修正ができるようにする
//課題：適切な設定ではない場合には次へいけないようにする
//タイプ型の配列を引数として受け取り、オブジェクト型配列に入力結果を書き込む
//課題：コンポーネントのサブクラスを選択できるようにする。
//課題：入力が存在しない場合（Step2→3遷移）にステップ数がエラーでも変化するためインデックスをオーバーしてしまい、前後に移動できなくなる。
////課題：ふくすうのクラスを生成できるようにする→おk
//課題：サブクラスの生成時にもExceptionを表示するようにする
//課題：Valueをドロップダウンリストにしてinstanceofで当てはまるインスタンスを表示する＆セットでそのインスタンスを用いることができるようにする。

public class ArgListPanel extends JPanel{

	//プリミティブ型：値入力を促す
	//参照型：新しくウィンドウを生成して入力を促す→参照型の引数が存在しないものを入力したらそのまま生成
	Type[] typee;
	Object[] arg;	//インスタンス生成に必要なインスタンスを格納する→タイプ型の要素数と同じはず
	JPanel 		mainPanel	= new JPanel();				//引数設定用パネル
	JTextArea 	notifyPanel = new JTextArea();			//通知用パネル
	JPanel 		btnPanel	= new JPanel();				//ボタン配置パネル
	JButton		finish		= new JButton("Check parameter");
	public static final String SPACE = "          ";
	
	public ArgListPanel(Type[] typee) {
		
		this.typee = typee;		//引数情報の格納
		
		//パネルの初期化
		makeNotifyPanel();		//通知パネルの初期化
		makeBtnPanel();			//ボタンパネルの初期化
		
		if(typee.length == 0){		//標準コンストラクタを選択した場合に入る
			System.out.println("標準コンストラクタを選択しました。");
			finishAction();
		}else{
			System.out.println("引数ありのコンストラクタを選択しました。");
//			for(Type t : typee){		//コンストラクタの引数が仕様通りの引数かどうかチェック
				//配列は生成されているがnullが入っている
//				Class<?> c = t.getClass();
//				int mod = c.getModifiers();
//				if((Modifier.isAbstract(mod)&&!c.isPrimitive())||Modifier.isInterface(mod)||c.isAnonymousClass()){
//					notifyPanel.setText("\nインスタンス化できないパラメータを含んでいます。\n"
//							+ "コンストラクタを選択し直して下さい。");
//				}
//			}
			arg = new Object[this.typee.length];	//引数をもとにインスタンスを生成するためのオブジェクト型配列の生成
			makeListPanel();
		}

		//パネル初期化
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(mainPanel);
		this.add(btnPanel);
		this.add(notifyPanel);
		
	}
	
	//通知パネルを作成するメソッド
	public void makeNotifyPanel(){
		notifyPanel.setEditable(false);
		notifyPanel.setBackground(this.getBackground());
		notifyPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
//		notifyPanel.setPreferredSize(new Dimension(10, 20));
	}
	
	//　ボタンパネルを作成するメソッド
	public void makeBtnPanel(){
		// 完了ボタンの追加
		finish.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkParam()) {
					finishAction();
				} else {
					rewriteAction();
				}
			}
		});
		btnPanel.add(finish);
		
	}
	
	// リストを作成するメソッド
	public void makeListPanel() {
		
		// グリッドレイアウトを用いた設定
		GridBagLayout layout = new GridBagLayout();
		mainPanel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5); // 余白設定

		//カラムの設定
		JTextField tname = new JTextField("Name");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(tname, gbc);
		tname.setEnabled(false);
		tname.setBackground(mainPanel.getBackground());
		tname.setBorder(new EmptyBorder(1, 1, 1, 1));

		JTextField tvalue = new JTextField("Value");
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(tvalue, gbc);
		tvalue.setEnabled(false);
		tvalue.setBackground(mainPanel.getBackground());
		tvalue.setBorder(new EmptyBorder(1, 1, 1, 1));

		JTextField tnum = new JTextField("Size or CONS");
		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(tnum, gbc);
		tnum.setEnabled(false);
		tnum.setBackground(mainPanel.getBackground());
		tnum.setBorder(new EmptyBorder(1, 1, 1, 1));

		JTextField tedit = new JTextField("Edit");
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(tedit, gbc);
		tedit.setEnabled(false);
		tedit.setBackground(mainPanel.getBackground());
		tedit.setBorder(new EmptyBorder(1, 1, 1, 1));

		mainPanel.add(tname);
		mainPanel.add(tvalue);
		mainPanel.add(tnum);
		mainPanel.add(tedit);

		///////////////引数一覧の表示/////////////
		//プリミティブ型　		◯名前　○値　×要素数　×編集
		//コンストラクタ　		◯名前　×値　○コンストラクタ選択　○編集
		//標準しかない場合		◯名前　×値　×コンストラクタ選択　×編集　→　生成してパラメータへ代入
		//配列型　	　　		◯名前　×値　◯要素数　◯編集
		//名前　値　要素数orコンストラクタ　編集　を順に配置する
		int i = 1;
		for (Type t : this.typee) {
			Class<?> clazz = (Class<?>) t;
			Constructor<?>[] consts = clazz.getConstructors();
			
			///////////////コンポーネントの初期化（共通箇所）//////////////
			// 名前フィールド設定
			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.anchor = GridBagConstraints.EAST;
			JTextField name = new JTextField(clazz.getSimpleName());
			name.setEnabled(false);
			name.setBackground(mainPanel.getBackground());
			name.setBorder(new EmptyBorder(1, 1, 1, 1));
			layout.setConstraints(name, gbc);
			// 値フィールド //JComboboxで作成したインスタンスも指定できるようにする
			gbc.gridx = 1;
			gbc.gridy = i;
			gbc.anchor = GridBagConstraints.EAST;
//			JTextField value = new JTextField();
//			value.setPreferredSize(new Dimension(60, 20));
			DefaultComboBoxModel<String> valuemodel = new DefaultComboBoxModel<String>();
			JComboBox<String> value = new JComboBox<String>(valuemodel);
			value.setPreferredSize(new Dimension(120, 20));
			layout.setConstraints(value, gbc);
			//　入力フィールド //すべてJComboboxで値の選択&取得を行う
			gbc.gridx = 2;
			gbc.gridy = i;
			gbc.anchor = GridBagConstraints.EAST;
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
			JComboBox<String> fill = new JComboBox<String>(model);
			fill.setPreferredSize(new Dimension(120, 20));
			layout.setConstraints(fill, gbc);
			// 編集フィールド
			gbc.gridx = 3;
			gbc.gridy = i;
			gbc.anchor = GridBagConstraints.EAST;
			JButton edit = new JButton();
			edit.setPreferredSize(new Dimension(60, 20));
			layout.setConstraints(edit, gbc);			
			
			/////////////コンポーネントの設定////////////　
			//プリミティブ型　文字列型　参照型（標準コンストラクタ）　参照型（コンストラクタ複数）　参照型配列で場合分け
			if(clazz.isPrimitive()){			//プリミティブ型の場合
				//◯名前　○値　×要素数　◯セット
				System.out.println("プリミティブ型の場合の初期化処理です。");
				value.setEnabled(true);
				fill.setEditable(false);
				fill.setEnabled(false);
				edit.setText("Set");
				edit.setEnabled(true);
				
			}else if(clazz.isArray()){			//配列型の場合
				//◯名前　×値　◯要素数　◯編集
				System.out.println("配列型の場合の初期化処理です。");
				value.setEnabled(false);
				fill.setEditable(true);	
				fill.setSelectedItem(new String("1"));
				edit.setText("Edit");
				edit.setEnabled(true);
				
			}else if(clazz==String.class){		//文字列型の場合
				//◯名前　◯値　×要素数　◯セット
				System.out.println("文字列型の場合の初期化処理です。");
				value.setEnabled(true);
				fill.setEditable(false);
				fill.setEnabled(false);
				edit.setText("Set");
				edit.setEnabled(true);
				
			}else if(clazz==Object.class){		//オブジェクト型の場合
				//◯名前　◯値　×要素数　◯セット
				System.out.println("オブジェクト型の場合の初期化処理です。");
				value.setEnabled(false);
				fill.setEditable(false);
				fill.setEnabled(false);
				edit.setText("Set");
				edit.setEnabled(true);
				
			}else{								//参照型の場合
				if(consts.length==0){			//標準コンストラクタしか存在しない場合
					//◯名前　×値　×コンストラクタ選択　◯セット
					System.out.println("標準コンストラクタしか存在しない場合の初期化処理です。");
					value.setEnabled(false);
					fill.setEditable(false);
					fill.setEnabled(false);
					edit.setText("Set");
					edit.setEnabled(true);
					
				}else{							//複数のコンストラクタが存在する場合
					//◯名前　×値　○コンストラクタ選択　○編集
					System.out.println("複数のコンストラクタが存在する場合の初期化処理です。");
					value.setEnabled(false);
					for(Constructor<?> c : consts){
						model.addElement(c.toGenericString());
					}
					edit.setText("Edit");
					edit.setEnabled(true);
					
				}
			}
			
			//メモ：すべてのリストにおいてボタンをおす動作が必要→クリックハンドラによる場合わけ
			//プリミティブ型and標準コンストラクタ、文字列の場合（Set）にはパラメーたをオブジェクト型配列に格納する必要がある
			//それ以外の場合には新たにパネルを生成
			
//			edit.addActionListener(new EditActionListener(clazz, fill, edit, value, i-1));
			edit.addMouseListener(new EditMouseListener(clazz, fill, edit, value, i-1));
			mainPanel.add(name);
			mainPanel.add(value);
			mainPanel.add(fill);
			mainPanel.add(edit);

			i++;
		}
	}
	
	//パラメータ取得メソッド
	public Object[] getParam(){
		return this.arg;
	}
	//パラメータがすべて入力されているかチェックするメソッド
	public boolean checkParam(){
		System.out.println(arg.length + "(長さ)");
		for(Object obj : arg){
			if(obj == null){
				return false;
			}
		}
		return true;
	}
	//クラスからラッパークラスのインスタンスを生成するメソッド
	public Object parsePrimitive(Class<?> clazz, String str) throws NumberFormatException{
		
		System.out.println(str + "を" + clazz.getSimpleName() + "に変換します。");
		
		if(clazz == byte.class){
			return new Byte(str);
		}else if(clazz == short.class){
			return new Short(str);
		}else if(clazz == int.class){
			return new Integer(str);
		}else if(clazz == long.class){
			return new Long(str);
		}else if(clazz == char.class){
			return new Character(str.charAt(0));
		}else if(clazz == float.class){
			return new Float(str);
		}else if(clazz == double.class){
			return new Double(str);
		}else if(clazz == boolean.class){
			return new Boolean(str);
		}else{
			System.out.println("Error!!: " + str + "を" + clazz.getSimpleName() + "に変換できませんでした。");
			return null;
		}
	}

	public void finishAction(){
		notifyPanel.setText( SPACE + "設定が完了しました。”Next” ボタンを押して下さい。");
//		finish.setEnabled(false);
		
	}
	public void rewriteAction(){
		notifyPanel.setText( SPACE + "設定が未完了です。引数の設定をして下さい。");
	}
	
	//Editボタンが押されたときの操作
	public class EditMouseListener implements MouseListener{
		
		private Class<?> clazz;				//対象とするクラスの格納
		private JComboBox<String> comb;		//対象の記入コンポーネント
		private JButton btn;				//対象の編集コンポーネント
		private JTextField val;				//対象の値コンポーネント
		private int ind;					//対象のインデックス
		private Constructor<?> targetcons;
		
		EditMouseListener(Class<?> clazz, JComboBox<String> comb, JButton btn, JTextField val, int ind){
			super();
			this.clazz = clazz;
			this.comb = comb;
			this.btn = btn;
			this.val = val;
			this.ind = ind;
		}	
		
		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			if(btn.getText() == "Set"){		//Setの場合
				if(clazz.isPrimitive()){			//プリミティブ型の場合
					try{
						arg[ind] = parsePrimitive(this.clazz, this.val.getText());
					}catch(NumberFormatException ee){
						notifyPanel.setText(SPACE + "Error: " + ee.getClass().getName());
					}
					System.out.println("プリミティブ型の値を設定しました。");
					
				}else if(clazz == String.class){	//文字列型の場合
					arg[ind] = this.val.getText();
					System.out.println("文字列を設定しました。");
					
				}else{								//参照型（標準コンストラクタしかない）場合
					try {
						arg[ind] = clazz.newInstance();
						System.out.println("標準コンストラクタのインスタンスを生成しました。");
						
					} catch (Exception ee) {
						System.out.println("標準コンストラクタのインスタンスを生成できませんでした。\n"
								+ ee.getClass().getName());
						ee.printStackTrace();
						notifyPanel.setText(SPACE + "Error: " + ee.getClass().getName());
					}
				}
			}else{							//Editの場合
				if(clazz.isArray()){		//配列の場合
					System.out.println("配列の設定です。");
					int arraynum = 1;
					try{
						arraynum = Integer.valueOf((String)comb.getSelectedItem());
					}catch(NumberFormatException ee){
						System.out.println("入力が値ではありませんでした。\n"
								+ ee.getClass().getName());
						notifyPanel.setText(SPACE + "Error: " + ee.getClass().getName());
					}finally{
						Type[] types = new Type[arraynum];
						//配列が仕様通りの引数かチェック
						int mod = clazz.getComponentType().getModifiers();
///////////////要修正：プリミティブ型は生成できるようにする（インスタンス化できないものをフィルタリングする。）
//						System.out.println(clazz.getComponentType().getSimpleName());
//						if((Modifier.isAbstract(mod)&&!clazz.getComponentType().isPrimitive())||Modifier.isInterface(mod)||clazz.isAnonymousClass()){
//							notifyPanel.setText( "\n" + SPACE + "インスタンス化できないパラメータを含んでいます。\n"
//									 + SPACE + "コンストラクタを選択し直して下さい。");
//						}else{
//							for(int i = 0; i<arraynum; i++ ){
//								types[i] = clazz.getComponentType();
//							}
//						}
						for(int i = 0; i<arraynum; i++ ){
							types[i] = clazz.getComponentType();
						}
						SubArgListDialog dialog = new SubArgListDialog(types);
						Point point = e.getLocationOnScreen();
						dialog.setBounds(point.x, point.y, 500, 250);
						dialog.setVisible(true);
						arg[this.ind] = dialog.getPram();
						System.out.println("配列の設定を行いました。");
						
					}
					
				}else{						//参照型の場合
					System.out.println("参照型の設定です。");
					Constructor<?>[] consts = this.clazz.getConstructors();
					targetcons = null;
					Type[] types = null;
					for(Constructor<?> cons : consts){
						if(comb.getSelectedItem().equals(cons.toGenericString())){
							System.out.println("対象のコンストラクタを見つけました。");
							types = cons.getGenericParameterTypes();
							targetcons = cons;
						}
					}
					if(types != null){
///////////////////////////////コンストラクタの引数が仕様通りの値かチェック
//参照型→インタフェースか抽象クラスの場合→サブクラスが存在すれば編集で生成できるようにする
//サブクラスを検索→サブクラスも一覧に表示する→キャストする必要ある？
//インタフェースでも生成を受け付ける→生成したオブジェクトだけは参照できるように変更する
//						for(Type t : typee){
//							Class<?> c = (Class<?>)t;
//							int mod = c.getModifiers();
//							if((Modifier.isAbstract(mod)&&!c.isPrimitive())||Modifier.isInterface(mod)||c.isAnonymousClass()){
//								notifyPanel.setText("\n"+ SPACE + "インスタンス化できないパラメータを含んでいます。\n"
//										+  SPACE + "コンストラクタを選択し直して下さい。");
//								return;
//							}
//						}
						SubArgListDialog dialog = new SubArgListDialog(types);
						Point point = e.getLocationOnScreen();
						dialog.setBounds(point.x, point.y, 500, 250);
						dialog.setVisible(true);
						if(types.length == 0){		//もしも標準コンストラクタの場合（生成してウィンドウはすぐに終了させる）
							try {
								arg[this.ind] = clazz.newInstance();
								System.out.println("標準コンストラクタのインスタンスを生成しました。");
								
							} catch (Exception ee) {
								System.out.println("標準コンストラクタのインスタンスを生成できませんでした。\n"
										+ ee.getMessage());
								ee.printStackTrace();
								notifyPanel.setText(SPACE + "Error: " + ee.getClass().getName());
							}
						}else{						//それ以外のコンストラクタを選択した場合
							//パラメータをもとにインスタンスの生成を行う
							Object[] subarg = dialog.getPram();
							try {
								arg[this.ind] = targetcons.newInstance(subarg);
								System.out.println("インスタンス生成に必要な引数のインスタンス生成に成功しました。");
							} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
									| InvocationTargetException e1) {
								System.out.println("インスタンスの生成に必要な引数のインスタンス生成に失敗しました。\n"
										+ e1.getClass().getName());
								e1.printStackTrace();
								notifyPanel.setText(SPACE + "Error: " + e1.getClass().getName());
							}
						}
						System.out.println("対象のコンストラクタからインスタンスを生成し、パラメータに値を設定しました。");	
					}else{
						System.out.println("対象のコンストラクタを見つけることができませんでした。");
					}
					
				}
			}
			
		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
		
	}
	
	
}
