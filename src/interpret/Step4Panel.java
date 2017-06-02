package interpret;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * コンストラクタの引数の設定
 * 
 * @author YoshikazuMurase
 *
 */
public class Step4Panel extends StepBasePanel {

	ArgListPanel panel; // コンストラクタ設定用パネル
	Object[] argobj; // パラメータ格納用パネル
	Class<?> clazz;
	Constructor<?> c;
	Type[] typee;
	int arraySize;
	ArgListPanel[] panels;
	Object[] conditions;
	Object[][] argobjs;
	Object[] instances;
	JTabbedPane tab = new JTabbedPane();
	Object arrayInstance;
	ShareInstance share;

	int count = 0;
	int ID;

	Step4Panel(HashMap<String, Object> parameter) {
		super(parameter);
	}

	@Override
	public void init() {

		// 現在までの設定情報を現在のパラメータに保存
		// 保持情報：クラス名、コンストラクタ、引数、引数の値
		arraySize = (Integer) getParameter(ParameterConst.NUM_ARRAY_ELEMENT);
		clazz = (Class<?>) getParameter(ParameterConst.CLASS_NAME);
		c = (Constructor<?>) getParameter(ParameterConst.CONST_NAME);
		typee = c.getGenericParameterTypes();
		setParameter(ParameterConst.CONST_PARAMETERS, typee);
		titlePanel.setText(TITLE_SPACE + "引数の設定");
		descriPanel.setText(SPACE + c.toGenericString() + "\n" + SPACE + "のコンストラクタの引数を設定してください\n\n" + SPACE
				+ "基本型の場合は値を入力した後 ”Set” を押して下さい。\n" + SPACE + "配列の場合は要素数を入力した後 ”Edit” を押して下さい。\n" + SPACE
				+ "参照型の場合は ”Edit” を押して引数の設定を行います。\n" + SPACE + "＊標準コンストラクタしか存在しない場合には ”Set” を押して下さい。\n");

		// 設定の保存に必要なフィールドの初期化
		panels = new ArgListPanel[arraySize];
		conditions = new Object[arraySize];
		argobjs = new Object[arraySize][];
		instances = new Object[arraySize];
		arrayInstance = Array.newInstance(clazz, arraySize); // インスタンスを入れる配列の生成
		for (int i = 0; i < arraySize; i++) {
			conditions[i] = new String("Don't use");
		}

		// 初期化とタブの生成
		for (int i = 0; i < arraySize; i++) {
			if (typee.length == 0) { // 標準コンストラクタを選択した場合
				argobjs[i] = null;
			} else { // それ以外を選択した場合
				argobjs[i] = new Object[typee.length];
			}
			addTab(i);
		}
		mainPanel.add(tab);

	}

	/**
	 * 配列の要素数に合わせて編集タブを生成するためのメソッド
	 * 
	 * @param i
	 */
	public void addTab(int i) {

		// タブに貼付けるベースのパネルの生成
		JPanel base = new JPanel();
		base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
		JPanel sub = new JPanel();
		sub.setLayout(new FlowLayout(FlowLayout.RIGHT));

		// 既存のクラス or 使用しない or nullを選択できるようにするパネルの生成
		JTextField name = new JTextField("Use exsisting instance or null:  ");
		name.setEditable(false);
		name.setBackground(getBackground());
		name.setBorder(new EmptyBorder(2, 2, 2, 2));
		DefaultComboBoxModel<ShareInstance> existmodel = new DefaultComboBoxModel<>();
		JComboBox<ShareInstance> existfill = new JComboBox<>(existmodel);
		existfill.setSize(new Dimension(10, 20));
		existmodel.addElement(new ShareInstance("Don't use", null));
		System.out.println("共有インスタンスの数：" + GrobalDataStore.getSize());
		for (int k = 0; k < GrobalDataStore.getSize(); k++) {
			existmodel.addElement(GrobalDataStore.getData(String.valueOf(k)));
		}
		existmodel.addElement(new ShareInstance("null", null));
		existfill.addActionListener(new ActionListener() { // 既存のクラスornullが選択された場合のアクションリスナー

			@Override
			public void actionPerformed(ActionEvent e) {
				ShareInstance einst = (ShareInstance) existmodel.getSelectedItem();
				conditions[i] = einst.getName();
				instances[i] = einst.getInstance();
			}
		});

		// 配列の数 or 単体の場合でも１つのタブを生成するパネルの生成
		panels[i] = new ArgListPanel(typee);

		// ベースパネルに貼付けてタブに追加
		sub.add(name);
		sub.add(existfill);
		base.add(panels[i]);
		base.add(sub);

		tab.addTab("Element" + String.valueOf(i), base);

		System.out.println("タブを追加しました！");

	}

	/**
	 * 引数をもとにインスタンスを生成する。 標準コンストラクタ以外を選択したのに引数がnullの場合には例外を出力する
	 * 既存のインスタンスが存在する場合には新しいインスタンスで上書きされないようにする必要がある。
	 */
	public boolean nextHandler() {

		for (int i = 0; i < arraySize; i++) {
			if (!createInstance(i))
				return false;
		}
		setParameter(ParameterConst.CONST_PARAM_OBJS, this.argobjs);
		setParameter(ParameterConst.INST_VALUES, this.instances);

		if (!checkAllInstance())
			return false;

		// 引数の格納と引数からインスタンスの生成
		if ((Boolean) getParameter(ParameterConst.IS_ARRAY)) { // 配列を生成し、共有データストアに格納
			try {
				Object array = getArray(clazz, instances);
				share = new ShareInstance(array.getClass().getName(), array);
				// GrobalDataStore.add(share);
				setParameter(ParameterConst.INST_VALUE, array);
			} catch (Exception ee) {
				System.out.println("配列のインスタンスを作成する際に例外が発生しました。");
				showErrorDialog(ee.getClass().getName());
				ee.printStackTrace();
				return false;
			}
		} else { // インスタンスをそのまま共有データストアに格納
			if (instances[0] != null) {
				share = new ShareInstance(instances[0].getClass().getName(), instances[0]);
				// GrobalDataStore.add(share); //インスタンスのフィールドを変更することで設定情報が反映される
			}
			setParameter(ParameterConst.INST_VALUE, instances[0]);
		}
		count++;
		if (count == 1) { // 初回の場合
			ID = GrobalDataStore.getNum();
			GrobalDataStore.add(String.valueOf(ID), share);
		} else { // １回目以降の場合
			GrobalDataStore.replace(String.valueOf(ID), share);
		}
		return true;
	}

	/**
	 * 引数をもとにインスタンスを生成するためのメソッド
	 * 
	 * @param ind
	 * @return
	 */
	public boolean createInstance(int ind) {

		// 状態の取得
		Object instance = instances[ind]; // 既存のインスタンスを使用する場合のインスタンス
		String ename = (String) conditions[ind]; // 既存のインスタンスを使用するかどうかの状態
		argobjs[ind] = panels[ind].getParam(); // 新規生成する場合のパラメータ

		try {
			if (ename.equals("Don't use")) { // パラメータを使用したインスタンスの生成

				System.out.println("パラメータを使用してインスタンスを生成します。");
				if (typee.length == 0) { // 標準コンストラクタの場合の処理
					instance = clazz.newInstance();
				} else { // それ以外のコンストラクタの処理
					Object[] argss = argobjs[ind];
					for (Object o : argss) {
						System.out.println(o.getClass());
					}
					instance = c.newInstance(argobjs[ind]); // test
				}
				System.out.println("インスタンスの生成に成功しました！！！！！");
				instances[ind] = instance;
			} else if (ename.equals("null")) { // インスタンスを生成しない

				System.out.println("インスタンスを生成しません");
				instances[ind] = null;
			} else { // 既存のインスタンスを挿入(コンボボックスで選択された時点でインスンスに格納される)

				System.out.println("既存のインスタンスを使用して初期化します。");
			}
			System.out.println("インスタンスの設定が完了しました。");
			return true;
		} catch (InstantiationException e) {
			e.printStackTrace();
			showErrorDialog(e.getClass().getName());
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			showErrorDialog(e.getClass().getName());
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			showErrorDialog(e.getClass().getName());
		} catch (InvocationTargetException e) {
			e.printStackTrace();
			showErrorDialog(e.getTargetException().getClass().getName());
		}
		return false;

	}

	/**
	 * 状態に応じて正しい値がパラメータに格納されているかをチェックするメソッド
	 * 
	 * @return
	 */
	public boolean checkAllInstance() {
		for (int i = 0; i < arraySize; i++) {
			if (conditions[i].equals("null")) {
				if (instances[i] != null)
					return false;
			} else {
				if (instances[i] == null)
					return false;
			}
		}
		return true;
	}

	@Override
	public void clear() {
		titlePanel.removeAll();
		descriPanel.removeAll();
		mainPanel.removeAll();
		notifyPanel.removeAll();
		notifyPanel.setText("");
		this.argobj = null;
		setParameter(ParameterConst.CONST_PARAMETERS, null);
		setParameter(ParameterConst.CONST_PARAM_OBJ, null);
		setParameter(ParameterConst.INST_VALUE, null);
		tab.removeAll();
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

}
