package interpret;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.swing.BoxLayout;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Step5Panel extends StepBasePanel{
	
	Class<?> clazz 			= null;
	Constructor<?> consts 	= null;
	Object[] argparam 		= null;
	Object instance 		= null;
	Field[] fields 			= null;
	Method[] methods 		= null;
	Object[] values 		= null;
	JTextField subtitle1 = new JTextField("　　・フィールドの操作");
	JTextField subtitle2 = new JTextField("　　・メソッドの実行");
	
	
	Step5Panel(){
		titlePanel.setText(TITLE_SPACE + "インスタンスの操作");
	}

	@Override
	public void init() {
		
		//各種一覧の取得
		clazz = (Class<?>) getBfParameter().get(ParameterConst.CLASS_NAME);
		consts = (Constructor<?>) getBfParameter().get(ParameterConst.CONST_NAME);
		argparam = (Object[])getBfParameter().get(ParameterConst.CONST_PARAM_OBJ); 
		fields = clazz.getFields();
		setParameter(ParameterConst.CLASS_FIELD, fields);
		methods = clazz.getMethods();
		setParameter(ParameterConst.CLASS_METHOD, methods);
		
		//インスタンスの生成
		try {
			if(argparam == null){
				instance = clazz.newInstance();
			}else{
				instance = consts.newInstance(argparam);
			}
			descriPanel.setText(SPACE + "インスタンスの生成に成功しました。\n"
								+ SPACE + "以下のリストかからフィールドの操作やメソッドの実行ができます。");
			System.out.println("インスタンスの生成に成功しました！！！！！");
		} catch (InstantiationException e) {
			descriPanel.setText(SPACE + "インスタンスの生成に失敗しました。\n"
					+ SPACE + e.getClass().getName());
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			descriPanel.setText(SPACE + "インスタンスの生成に失敗しました。\n"
					+ SPACE + e.getClass().getName());
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			descriPanel.setText(SPACE + "インスタンスの生成に失敗しました。\n"
					+ SPACE + e.getClass().getName());
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			descriPanel.setText(SPACE + "インスタンスの生成に失敗しました。\n"
					+ SPACE + e.getClass().getName());
			e.printStackTrace();
		}
		
		//インスタンスの現在のフィールド値を取得
		values = getInstValue();
		
		//フィールドと値の表示
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		
		FieldsPanel fpanel = new FieldsPanel(instance);
		MethodsPanel mpanel = new MethodsPanel(instance);
		
		setSubTitle();
		
		mainPanel.add(subtitle1);
	    mainPanel.add(fpanel);
		mainPanel.add(subtitle2);
	    mainPanel.add(mpanel);

		//インスタンスのメソッド表示
		
		//インスタンスのフィールド一覧表示andメソッド表示
		
	}
	
	public void setSubTitle(){
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
	
	
	public Object[] getInstValue(){
		Object[] vals = new Object[fields.length];
		int i = 0;
		for(Field field : fields){
			try {
				vals[i] = field.get(instance);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			i++;
		}
		return vals;
	}

	@Override
	public void nextHandler() {
		
	}

	@Override
	public void clear() {
		titlePanel.removeAll();
		descriPanel.removeAll();
		mainPanel.removeAll();
		notifyPanel.removeAll();
		
	}

}
