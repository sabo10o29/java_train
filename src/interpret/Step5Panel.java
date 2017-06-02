package interpret;

import java.awt.Font;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * インスタンスに対する操作パネル
 * 
 * @author YoshikazuMurase
 *
 */
public class Step5Panel extends StepBasePanel {

	Class<?> clazz = null;
	Constructor<?> consts = null;
	Object[] argparam = null;
	Object instance = null;
	Field[] fields = null;
	Method[] methods = null;
	Object[] values = null;
	boolean isArray;
	int arraySize;
	JTextField subtitle1 = new JTextField("　　・フィールドの操作");
	JTextField subtitle2 = new JTextField("　　・メソッドの実行");
	JTabbedPane tab = new JTabbedPane();

	Step5Panel(HashMap<String, Object> parameter) {

		super(parameter);
		titlePanel.setText(TITLE_SPACE + "インスタンスの操作");
		descriPanel.setText(SPACE + "配列の場合には配列の要素を表示します");
	}

	@Override
	public void init() {

		// 各種一覧の取得
		clazz = (Class<?>) getParameter(ParameterConst.CLASS_NAME);
		consts = (Constructor<?>) getParameter(ParameterConst.CONST_NAME);
		argparam = (Object[]) getParameter(ParameterConst.CONST_PARAM_OBJ);
		fields = clazz.getFields();
		setParameter(ParameterConst.CLASS_FIELD, fields);
		methods = clazz.getMethods();
		setParameter(ParameterConst.CLASS_METHOD, methods);
		instance = getParameter(ParameterConst.INST_VALUE);
		isArray = (Boolean) getParameter(ParameterConst.IS_ARRAY);
		arraySize = (Integer) getParameter(ParameterConst.NUM_ARRAY_ELEMENT);

		setSubTitle();
		// 配列の数、またはインスタンスをタブで表示
		for (int i = 0; i < arraySize; i++) {
			addTab(i);
		}

		mainPanel.add(tab);
	}

	/**
	 * 現在のインスタンスを操作するためのパネルを作成し、タブに追加するためのメソッド
	 * 
	 * @param i
	 */
	public void addTab(int i) {

		JPanel base = new JPanel();
		base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));

		if (isArray) { // 配列の場合
			Object inst = Array.get(instance, i);
			if (inst != null) { // 要素が存在する場合
				FieldsPanel fpanel = new FieldsPanel(inst);
				MethodsPanel mpanel = new MethodsPanel(inst);
				base.add(subtitle1);
				base.add(fpanel);
				base.add(subtitle2);
				base.add(mpanel);
			} else {

			}

		} else { // 単体のインスタンスの場合
			if (instance != null) { // 要素が存在する場合
				FieldsPanel fpanel = new FieldsPanel(instance);
				MethodsPanel mpanel = new MethodsPanel(instance);
				base.add(subtitle1);
				base.add(fpanel);
				base.add(subtitle2);
				base.add(mpanel);
			}
		}

		tab.addTab("Element" + String.valueOf(i), base);
	}

	/**
	 * サブタイトル用のフォント設定
	 */
	public void setSubTitle() {
		Font font = new Font("Arial", Font.BOLD, 15);

		subtitle1.setFont(font);
		subtitle2.setFont(font);

		subtitle1.setEditable(false);
		subtitle2.setEditable(false);

		subtitle1.setBorder(new EmptyBorder(2, 2, 2, 2));
		subtitle2.setBorder(new EmptyBorder(2, 2, 2, 2));

		subtitle1.setBackground(super.getBackground());
		subtitle2.setBackground(super.getBackground());
	}

	/**
	 * フィールドの値を取得するメソッド
	 * 
	 * @return
	 */
	// public Object[] getInstValue(){
	// Object[] vals = new Object[fields.length];
	// int i = 0;
	// for(Field field : fields){
	// try {
	// vals[i] = field.get(instance);
	// } catch (IllegalArgumentException e) {
	// e.printStackTrace();
	// showErrorDialog(e.getClass().getName());
	// } catch (IllegalAccessException e) {
	// e.printStackTrace();
	// showErrorDialog(e.getClass().getName());
	// }
	// i++;
	// }
	// return vals;
	// }

	@Override
	public boolean nextHandler() {
		// 次のパネルが存在しないためfalse
		return false;
	}

	@Override
	public void clear() {
		titlePanel.removeAll();
		descriPanel.removeAll();
		mainPanel.removeAll();
		notifyPanel.removeAll();
		setParameter(ParameterConst.CLASS_FIELD, null);
		setParameter(ParameterConst.CLASS_METHOD, null);
		tab.removeAll();

	}
}
