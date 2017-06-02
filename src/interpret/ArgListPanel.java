package interpret;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseListener;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//エラーはポップアップで表示
//!!!!!引数に配列を含むインスタンスの生成ができない
//課題：配列の数が多い場合にスクロールばーで表示する
/**
 * 設定パネル：渡されたターゲットの引数をもとに引数の設定を行うパネル 仕様：例外が発生した場合にはその例外名をポップアップウィンドウに表示する
 * 
 * @author YoshikazuMurase
 *
 */
public class ArgListPanel extends JPanel implements ErrorDialog {

	// プリミティブ型：値入力を促す
	// 参照型：新しくウィンドウを生成して入力を促す→参照型の引数が存在しないものを入力したらそのまま生成
	Type[] typee = null;
	Object[] arg = null; // インスタンス生成に必要なインスタンスを格納する→タイプ型の要素数と同じはず
	JPanel mainPanel = new JPanel(); // 引数設定用パネル
	JPanel btnPanel = new JPanel(); // ボタン配置パネル
	JButton finish = new JButton("Check parameter");
	public static final String SPACE = "          ";

	/**
	 * //コンストラクタ 標準コンストラクタ：：nullのまま それ以外：：引数変数に”インスタンス”が入る
	 * 
	 * @param typee
	 */
	public ArgListPanel(Type[] typee) {

		this.typee = typee; // 引数情報の格納

		if (typee.length == 0) { // 標準コンストラクタを選択した場合に入る
			System.out.println("標準コンストラクタを選択しました。");
		} else {
			System.out.println("引数ありのコンストラクタを選択しました。");
			arg = new Object[this.typee.length]; // 引数をもとにインスタンスを生成するためのオブジェクト型配列の生成
			makeListPanel();
		}

		// パネル初期化
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.add(mainPanel);
		this.add(btnPanel);

	}

	/**
	 * リストを作成するメソッド 名前 値 要素数orコンストラクタ 編集 を順に配置する プリミティブ型 ◯名前 ○値 ×要素数 ◯既存インスタンス
	 * ×編集 コンストラクタ ◯名前 ×値 ○コンストラクタ選択 ◯既存インスタンス ○編集 標準しかない場合 ◯名前 ×値 ×コンストラクタ選択
	 * ◯既存インスタンス ×編集 配列型 ◯名前 ×値 ◯要素数 ◯既存インスタンス ◯編集
	 * 
	 */
	public void makeListPanel() {

		// グリッドレイアウトを用いた設定
		GridBagLayout layout = new GridBagLayout();
		mainPanel.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5); // 余白設定

		// カラムの設定
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

		JTextField texist = new JTextField("Use exist inst");
		gbc.gridx = 3;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(texist, gbc);
		texist.setEnabled(false);
		texist.setBackground(mainPanel.getBackground());
		texist.setBorder(new EmptyBorder(1, 1, 1, 1));

		JTextField tedit = new JTextField("Edit");
		gbc.gridx = 4;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.CENTER;
		layout.setConstraints(tedit, gbc);
		tedit.setEnabled(false);
		tedit.setBackground(mainPanel.getBackground());
		tedit.setBorder(new EmptyBorder(1, 1, 1, 1));

		mainPanel.add(tname);
		mainPanel.add(tvalue);
		mainPanel.add(tnum);
		mainPanel.add(texist);
		mainPanel.add(tedit);

		/////////////// コンポーネントの初期化（共通箇所）//////////////
		int i = 1;
		for (Type t : this.typee) {
			Class<?> clazz = (Class<?>) t;
			Constructor<?>[] consts = clazz.getConstructors();

			// 名前フィールド設定
			gbc.gridx = 0;
			gbc.gridy = i;
			gbc.anchor = GridBagConstraints.EAST;
			JTextField name = new JTextField(clazz.getSimpleName());
			name.setEnabled(false);
			name.setBackground(mainPanel.getBackground());
			name.setBorder(new EmptyBorder(1, 1, 1, 1));
			layout.setConstraints(name, gbc);
			// 値フィールド
			gbc.gridx = 1;
			gbc.gridy = i;
			gbc.anchor = GridBagConstraints.EAST;
			JTextField value = new JTextField();
			value.setPreferredSize(new Dimension(60, 20));
			value.setPreferredSize(new Dimension(120, 20));
			layout.setConstraints(value, gbc);
			// 入力フィールド //すべてJComboboxで値の選択&取得を行う
			gbc.gridx = 2;
			gbc.gridy = i;
			gbc.anchor = GridBagConstraints.EAST;
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
			JComboBox<String> fill = new JComboBox<String>(model);
			fill.setPreferredSize(new Dimension(120, 20));
			layout.setConstraints(fill, gbc);
			// 既存インスタンス使用フィールド
			gbc.gridx = 3;
			gbc.gridy = i;
			gbc.anchor = GridBagConstraints.EAST;
			DefaultComboBoxModel<ShareInstance> existmodel = new DefaultComboBoxModel<>();
			JComboBox<ShareInstance> existfill = new JComboBox<>(existmodel);
			existfill.setPreferredSize(new Dimension(120, 20));
			layout.setConstraints(existfill, gbc);
			// 編集フィールド
			gbc.gridx = 4;
			gbc.gridy = i;
			gbc.anchor = GridBagConstraints.EAST;
			JButton edit = new JButton();
			edit.setPreferredSize(new Dimension(60, 20));
			layout.setConstraints(edit, gbc);

			///////////// コンポーネントの設定////////////
			// プリミティブ型 文字列型 参照型（標準コンストラクタ） 参照型（コンストラクタ複数） 参照型配列で場合分け
			if (clazz.isPrimitive()) { // プリミティブ型の場合
				// ◯名前 ○値 ×要素数 ◯セット
				System.out.println("プリミティブ型の場合の初期化処理です。");
				value.setEnabled(true);
				fill.setEditable(false);
				fill.setEnabled(false);
				existfill.setEditable(false);
				existfill.setEnabled(true);
				edit.setText("Set");
				edit.setEnabled(true);

			} else if (clazz.isArray()) { // 配列型の場合
				// ◯名前 ×値 ◯要素数 ◯編集
				System.out.println("配列型の場合の初期化処理です。");
				value.setEnabled(false);
				fill.setEditable(true);
				fill.setSelectedItem(new String("1"));
				existfill.setEditable(false);
				existfill.setEnabled(true);
				edit.setText("Edit");
				edit.setEnabled(true);

			} else if (clazz == String.class) { // 文字列型の場合
				// ◯名前 ◯値 ×要素数 ◯セット
				System.out.println("文字列型の場合の初期化処理です。");
				value.setEnabled(true);
				fill.setEditable(false);
				fill.setEnabled(false);
				existfill.setEditable(false);
				existfill.setEnabled(true);
				edit.setText("Set");
				edit.setEnabled(true);

			} else if (clazz == Object.class) { // オブジェクト型の場合
				// ◯名前 ◯値 ×要素数 ◯セット
				System.out.println("オブジェクト型の場合の初期化処理です。");
				value.setEnabled(false);
				fill.setEditable(false);
				fill.setEnabled(false);
				existfill.setEditable(false);
				existfill.setEnabled(true);
				edit.setText("Set");
				edit.setEnabled(true);

			} else { // 参照型の場合
				if (consts.length == 0) { // 標準コンストラクタしか存在しない場合
					// ◯名前 ×値 ×コンストラクタ選択 ◯セット
					System.out.println("標準コンストラクタしか存在しない場合の初期化処理です。");
					value.setEnabled(false);
					fill.setEditable(false);
					fill.setEnabled(false);
					existfill.setEditable(false);
					existfill.setEnabled(true);
					edit.setText("Set");
					edit.setEnabled(true);

				} else { // 複数のコンストラクタが存在する場合
					// ◯名前 ×値 ○コンストラクタ選択 ○編集
					System.out.println("複数のコンストラクタが存在する場合の初期化処理です。");
					value.setEnabled(false);
					for (Constructor<?> c : consts) { // コンストラクタリストの追加
						model.addElement(c.toGenericString()); // 最初の値を選択したときの処理を記述
					}
					existfill.setEditable(false);
					existfill.setEnabled(true);
					edit.setText("Edit");
					edit.setEnabled(true);

				}
			}

			// イベントリスナーの追加
			// 既存インスタンスに関する実装
			existmodel.addElement(new ShareInstance("Don't use", null));
			System.out.println("共有インスタンスの数：" + GrobalDataStore.getSize());
			for (int k = 0; k < GrobalDataStore.getSize(); k++) {
				existmodel.addElement(GrobalDataStore.getData(String.valueOf(k)));
			}
			existfill.addItemListener(new ShareInstItemListener(fill, edit, value, existmodel, i - 1));
			// エディットorセットボタンに関する実装
			edit.addMouseListener(new EditMouseListener(clazz, fill, edit, value, i - 1));

			// コンポーネントの追加
			mainPanel.add(name);
			mainPanel.add(value);
			mainPanel.add(fill);
			mainPanel.add(existfill);
			mainPanel.add(edit);

			i++;
		}
	}

	/**
	 * アイテムリスナー 共有インスタンスから選択された場合の処理
	 * 
	 * @author YoshikazuMurase
	 *
	 */
	public class ShareInstItemListener implements ItemListener {

		private DefaultComboBoxModel<ShareInstance> existmodel;
		private int ind;
		private JComboBox<String> comb;
		private JButton btn;
		private JTextField val;

		public ShareInstItemListener(JComboBox<String> comb, JButton btn, JTextField val,
				DefaultComboBoxModel<ShareInstance> existmodel, int ind) {
			this.existmodel = existmodel;
			this.ind = ind;
			this.comb = comb;
			this.btn = btn;
			this.val = val;
		}

		@Override
		public void itemStateChanged(ItemEvent e) {
			if (e.getStateChange() == ItemEvent.SELECTED) {
				System.out.println("既存のインスタンスが選択されました。");
				ShareInstance inst = (ShareInstance) existmodel.getSelectedItem();

				if (inst.getName() == "Don't use") {
					this.comb.setEditable(true);
					this.btn.setEnabled(true);
					this.val.setEditable(true);
				} else {
					arg[this.ind] = inst.getInstance(); // 既存のインスタンスをパラメータに入力
														// //＠＠check：引数の入力
					this.comb.setEditable(false);
					this.btn.setEnabled(false);
					this.val.setEditable(false);
				}
			}
		}

	}

	/**
	 * マウスリスナー EditまたはSetボタンが押されたときの操作 引数が存在しない→Setボタン→インスタンスを引数にする
	 * 引数が存在する→Editボタン→新しい設定ウィンドウを表示→インスタンスを引数に設定する
	 * 
	 * @author YoshikazuMurase
	 *
	 */
	public class EditMouseListener implements MouseListener {

		private Class<?> clazz; // 対象とするクラスの格納
		private JComboBox<String> comb; // 対象の記入コンポーネント
		private JButton btn; // 対象の編集コンポーネント
		private JTextField val; // 対象の値コンポーネント
		private int ind; // 対象のインデックス
		private Constructor<?> targetcons;

		EditMouseListener(Class<?> clazz, JComboBox<String> comb, JButton btn, JTextField val, int ind) {
			super();
			this.clazz = clazz;
			this.comb = comb;
			this.btn = btn;
			this.val = val;
			this.ind = ind;
		}

		@Override
		public void mouseClicked(java.awt.event.MouseEvent e) {
			if (btn.getText() == "Set") { // Setの場合
				if (clazz.isPrimitive()) { // プリミティブ型の場合
					try {
						arg[ind] = parsePrimitive(this.clazz, this.val.getText()); // ＠＠check：引数の入力
					} catch (NumberFormatException ee) {
						System.out.println("プリミティブ型のインスタンス生成に失敗しました。");
						showErrorDialog(ee.getClass().getName());
					}
					System.out.println("プリミティブ型の値を設定しました。");

				} else if (clazz == String.class) { // 文字列型の場合
					arg[ind] = this.val.getText(); // ＠＠check：引数の入力
					System.out.println("文字列を設定しました。");

				} else { // 参照型（標準コンストラクタしかない）場合
					try {
						arg[ind] = clazz.newInstance(); // ＠＠check：引数の入力
						System.out.println("標準コンストラクタのインスタンスを生成しました。");

					} catch (Exception ee) {
						System.out.println("標準コンストラクタのインスタンスを生成できませんでした。\n" + ee.getClass().getName());
						ee.printStackTrace();
						showErrorDialog(ee.getClass().getName());
					}
				}
			} else { // Editの場合
				if (clazz.isArray()) { // 配列の場合
					System.out.println("配列の設定です。");
					int arraynum = 1;
					try {
						arraynum = Integer.valueOf((String) comb.getSelectedItem());
					} catch (NumberFormatException ee) {
						System.out.println("入力が値ではありませんでした。\n" + ee.getClass().getName());
						showErrorDialog(ee.getClass().getName());
					} finally {
						Type[] types = new Type[arraynum];
						for (int i = 0; i < arraynum; i++) {
							types[i] = clazz.getComponentType();
						}
						SubArgListDialog dialog = new SubArgListDialog(types);
						Point point = e.getLocationOnScreen();
						dialog.setBounds(point.x, point.y, 700, 250);
						dialog.setVisible(true);
						// 参照型配列のインスタンスをパラメータに格納
						try {
							Object[] vals = dialog.getParam();
							// プリミティブ型配列の場合にはラッパークラスへの変換が必要
							System.out.println("＠配列の要素数：" + vals.length);
							Object argument = getArray(clazz.getComponentType(), vals);
							arg[this.ind] = argument; //// ＠＠check：引数の入力
														//// 配列オブジェクトを代入する
							System.out.println("配列の設定を行いました。");
						} catch (Exception ee) {
							System.out.println("インスタンス生成に必要な配列の生成時に例外が発生しました。");
							showErrorDialog(ee.getClass().getName());
							ee.printStackTrace();
						}

					}

				} else { // 参照型の場合
					System.out.println("参照型の設定です。");
					Constructor<?>[] consts = this.clazz.getConstructors();
					targetcons = null;
					Type[] types = null;
					for (Constructor<?> cons : consts) {
						if (comb.getSelectedItem().equals(cons.toGenericString())) {
							System.out.println("対象のコンストラクタを見つけました。");
							types = cons.getGenericParameterTypes();
							targetcons = cons;
						}
					}
					if (types != null) {
						SubArgListDialog dialog = new SubArgListDialog(types);
						Point point = e.getLocationOnScreen();
						dialog.setBounds(point.x, point.y, 700, 250);
						dialog.setVisible(true);
						if (types.length == 0) { // もしも標準コンストラクタの場合（生成してウィンドウはすぐに終了させる）
							try {
								arg[this.ind] = clazz.newInstance();
								System.out.println("標準コンストラクタのインスタンスを生成しました。");

							} catch (Exception ee) {
								System.out.println("標準コンストラクタのインスタンスを生成できませんでした。\n" + ee.getMessage());
								ee.printStackTrace();
								showErrorDialog(ee.getClass().getName());
							}
						} else { // それ以外のコンストラクタを選択した場合
							// パラメータをもとにインスタンスの生成を行う
							Object[] subarg = dialog.getParam();
							try {
								arg[this.ind] = targetcons.newInstance(subarg);
								System.out.println("インスタンス生成に必要な引数のインスタンス生成に成功しました。");
							} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
									| InvocationTargetException e1) {
								System.out.println("インスタンスの生成に必要な引数のインスタンス生成に失敗しました。\n" + e1.getClass().getName());
								e1.printStackTrace();
								showErrorDialog(e1.getClass().getName());
							}
						}
						System.out.println("対象のコンストラクタからインスタンスを生成し、パラメータに値を設定しました。");
					} else {
						System.out.println("対象のコンストラクタを見つけることができませんでした。");
					}

				}
			}

		}

		@Override
		public void mousePressed(java.awt.event.MouseEvent e) {
		}

		@Override
		public void mouseReleased(java.awt.event.MouseEvent e) {
		}

		@Override
		public void mouseEntered(java.awt.event.MouseEvent e) {
		}

		@Override
		public void mouseExited(java.awt.event.MouseEvent e) {
		}
	}

	///////////// その他のメソッド////////////

	/**
	 * ターゲットのインスタンス生成に必要なパラメータの取得
	 * 
	 * @return パラメータ
	 */
	public Object[] getParam() {
		return this.arg;
	}

	/**
	 * プリミティブ型のクラスからラッパークラスのインスタンスを生成するメソッド
	 * 
	 * @param clazz
	 * @param str
	 * @return
	 * @throws NumberFormatException
	 */
	public Object parsePrimitive(Class<?> clazz, String str) throws NumberFormatException {

		System.out.println(str + "を" + clazz.getSimpleName() + "に変換します。");

		if (clazz == byte.class) {
			return new Byte(str);
		} else if (clazz == short.class) {
			return new Short(str);
		} else if (clazz == int.class) {
			return new Integer(str);
		} else if (clazz == long.class) {
			return new Long(str);
		} else if (clazz == char.class) {
			return new Character(str.charAt(0));
		} else if (clazz == float.class) {
			return new Float(str);
		} else if (clazz == double.class) {
			return new Double(str);
		} else if (clazz == boolean.class) {
			return new Boolean(str);
		} else {
			System.out.println("Error!!: " + str + "を" + clazz.getSimpleName() + "に変換できませんでした。");
			return null;
		}
	}

	/**
	 * プリミティブ型に対するラッパー型を取得するメソッド
	 * 
	 * @param clazz
	 * @return
	 * @throws NumberFormatException
	 */
	public Class<?> getRapper(Class<?> clazz) throws NumberFormatException {

		if (clazz == byte.class) {
			return Byte.class;
		} else if (clazz == short.class) {
			return Short.class;
		} else if (clazz == int.class) {
			return Integer.class;
		} else if (clazz == long.class) {
			return Long.class;
		} else if (clazz == char.class) {
			return Character.class;
		} else if (clazz == float.class) {
			return Float.class;
		} else if (clazz == double.class) {
			return Double.class;
		} else if (clazz == boolean.class) {
			return Boolean.class;
		} else {
			System.out.println("該当するラッパークラスがありませんでした。");
			return clazz;
		}
	}

	/**
	 * 与えられた配列とクラスから配列型のインスタンスを生成するメソッド
	 * 
	 * @param clazz
	 * @param vals
	 * @return
	 */
	public Object getArray(Class<?> clazz, Object[] vals) {

		Object argument = Array.newInstance(clazz, vals.length);

		int i = 0;
		for (Object v : vals) {
			if (clazz == byte.class) {
				Array.setByte(argument, i, (Byte) v);
			} else if (clazz == short.class) {
				Array.setShort(argument, i, (Short) v);
			} else if (clazz == int.class) {
				Array.setInt(argument, i, (Integer) v);
			} else if (clazz == long.class) {
				Array.setLong(argument, i, (Long) v);
			} else if (clazz == char.class) {
				Array.setChar(argument, i, (Character) v);
			} else if (clazz == float.class) {
				Array.setFloat(argument, i, (Float) v);
			} else if (clazz == double.class) {
				Array.setDouble(argument, i, (Double) v);
			} else if (clazz == boolean.class) {
				Array.setBoolean(argument, i, (Boolean) v);
			} else {
			}
			i++;
		}

		if (!clazz.isPrimitive())
			System.arraycopy(vals, 0, argument, 0, vals.length);

		return argument;
	}

	public int[] getPriArray(Integer[] val) {
		int[] result = new int[val.length];
		for (int i = 0; i < val.length; i++) {
			result[i] = val[i];
		}
		return result;
	}

	public byte[] getPriArray(Byte[] val) {
		byte[] result = new byte[val.length];
		for (int i = 0; i < val.length; i++) {
			result[i] = val[i];
		}
		return result;
	}

	@Override
	public void showErrorDialog(String message) {
		JLabel label = new JLabel("Error!: " + message);
		label.setForeground(Color.RED);
		JOptionPane.showMessageDialog(this, label);
	}

}
