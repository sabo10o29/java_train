package interpret;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Field;
import java.lang.reflect.Type;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FieldsPanel extends JPanel{
	
	Object instance = null;		//対象のインスタンス
	Field[] fields = null;		//対象のフィールド一覧
	Object[] values = null;		//対象のフィールドの値一覧
	int nowind = 0;		//フィールドのインデクス
	
	//各種コンポーネント
	JPanel base = new JPanel();							//　ベースパネル
	JPanel modify = new JPanel();						//　フィールド操作パネル
	JScrollPane sp = new JScrollPane();					//　一覧表示するためのスクロールパネル
	JButton set = new JButton("Set");					//　プリミティブ型変数設定用ボタン
	JButton edit = new JButton("Edit reference type");	//　参照型設定用ボタン
	JTextField notifyPanel = new JTextField();			//　通知用パネル
	JList<Object> fieldlist = null;						//　フィールド一覧表示コンポーネント
	
	//値表示パネルの初期化
	DefaultComboBoxModel<String> valuemodel = new DefaultComboBoxModel<String>();
	JComboBox<String> dispvalue = new JComboBox<String>(valuemodel);
	
	int n[] = {18, 29, 36, 12};		//テスト

	
	public FieldsPanel(Object instance){
		
		//初期化処理
		this.instance = instance;	//インスタンス
		this.fields = instance.getClass().getDeclaredFields();	//フィールド一覧の取得
		this.fieldlist = new JList<Object>(this.fields);	//
		this.fieldlist.addListSelectionListener(new ModifyFieldListener());		//
		this.sp.getViewport().setView(fieldlist);
	    this.sp.setPreferredSize(new Dimension(500, 100));
		this.set.addActionListener(new MyButtonListener());
		this.edit.addMouseListener(new EditObjectButtonListener());
		this.base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
		this.modify.setLayout(new BoxLayout(modify, BoxLayout.X_AXIS));
		this.dispvalue.setEditable(true);
		setNotifyPanel();
		
		modify.add(dispvalue);
		modify.add(set);
		modify.add(edit);
		base.add(sp);
		base.add(modify);
		base.add(notifyPanel);
		
		this.add(base);
		
	}
	
	//参照型の場合に編集するボタン
	class EditObjectButtonListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			Field nowfield = fields[nowind];
			nowfield.setAccessible(true);
			Type[] types = new Type[1];
			types[0] = nowfield.getType();
			SubArgListDialog dialog = new SubArgListDialog(types);
			Point point = e.getLocationOnScreen();
			dialog.setBounds(point.x, point.y, 500, 250);
			dialog.setVisible(true);
			try {
				//プリミティブ型配列の場合→オブジェクトをキャストして設定
				//参照型の場合→インスタンスを生成して設定→めんどくさい
				nowfield.set(instance, dialog.getPram()[0]);
				System.out.println("フィールド値の設定を行いました。");
				notifyPanel.setText("");
				
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				notifyPanel.setText("フィールドの設定を変更することができませんでした。\n"
						+ e1.toString());
				e1.printStackTrace();
			}
			
		}
		@Override
		public void mousePressed(MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
		@Override
		public void mouseExited(MouseEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
		
		
	}
	
	//セットボタンの処理（プリミティブ型の処理）
	//テキストフィールドの値をフィールドにセットする
	class MyButtonListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			Field nowfield = fields[nowind];
			nowfield.setAccessible(true);
			Class<?> clazz = nowfield.getType();
			String val = (String) dispvalue.getSelectedItem();
			Object resultval = parsePrimitive(clazz, val);
			try {
				nowfield.set(instance, resultval);
				System.out.println("フィールドの値を更新しました。");
				notifyPanel.setText("");
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				notifyPanel.setText("フィールドの設定を変更することができませんでした。\n"
						+ e1.toString());
				e1.printStackTrace();
			}
			
			
		}
		
	}
	
	//フィールドの値の表示
	class ModifyFieldListener implements ListSelectionListener{
		
		//リストの要素をクリックした時の処理
		@Override
		public void valueChanged(ListSelectionEvent e) {
			nowind = fieldlist.getSelectedIndex();	//フィールドのインデックスの取得
			Object val = null;
			try {
				val = fields[nowind].get(instance);
				System.out.println("フィールド値の取得に成功しました。");
				notifyPanel.setText("");
				
			} catch (IllegalArgumentException | IllegalAccessException e1) {
				notifyPanel.setText("フィールド値を取得することができませんでした。\n"
						+ e1.toString());
				e1.printStackTrace();
			}
			//フィールド値の表示
			if (val != null) {
				if(fields[nowind].getType().isPrimitive()){		//フィールドがプリミティブ型の場合
					valuemodel.removeAllElements();
					valuemodel.addElement(val.toString());
					set.setEnabled(true);
					edit.setEnabled(false);
				}else if(fields[nowind].getType().isArray()) { // フィールドが配列の場合
					System.out.println("このフィールドは配列です。");
					String[] strs = parseString(val, fields[nowind].getType().getComponentType());
					if(strs!=null){
						valuemodel.removeAllElements();
						for(String str : strs)valuemodel.addElement(str);
					}
					set.setEnabled(false);
					edit.setEnabled(true);
				} else { // フィールドが参照型の場合
					set.setEnabled(false);
					edit.setEnabled(true);
				}
			}
			
			
		}
		
	}
	
	// クラスからラッパークラスのインスタンスを生成するメソッド
	public String[] parseString(Object val, Class<?> clazz) {
		
		System.out.println("配列をString型の配列に変換します。");
		String[] result = null;
		
		if (clazz == byte.class) {
			
			byte[] v = (byte[])val;
			int l = v.length;
			result = new String[l];
			for(int i = 0; i<l; i++){
				result[i] = String.valueOf(v[i]);
			}
			
		} else if (clazz == short.class) {
			
			short[] v = (short[])val;
			int l = v.length;
			result = new String[l];
			for(int i = 0; i<l; i++){
				result[i] = String.valueOf(v[i]);
			}
			
		} else if (clazz == int.class) {

			int[] v = (int[])val;
			int l = v.length;
			result = new String[l];
			for(int i = 0; i<l; i++){
				result[i] = String.valueOf(v[i]);
			}
			
		} else if (clazz == long.class) {

			long[] v = (long[])val;
			int l = v.length;
			result = new String[l];
			for(int i = 0; i<l; i++){
				result[i] = String.valueOf(v[i]);
			}
			
		} else if (clazz == char.class) {
			
			char[] v = (char[])val;
			int l = v.length;
			result = new String[l];
			for(int i = 0; i<l; i++){
				result[i] = String.valueOf(v[i]);
			}
			
		} else if (clazz == float.class) {

			float[] v = (float[])val;
			int l = v.length;
			result = new String[l];
			for(int i = 0; i<l; i++){
				result[i] = String.valueOf(v[i]);
			}
			
		} else if (clazz == double.class) {

			double[] v = (double[])val;
			int l = v.length;
			result = new String[l];
			for(int i = 0; i<l; i++){
				result[i] = String.valueOf(v[i]);
			}
			
		} else if (clazz == boolean.class) {

			boolean[] v = (boolean[])val;
			int l = v.length;
			result = new String[l];
			for(int i = 0; i<l; i++){
				result[i] = String.valueOf(v[i]);
			}
			
		} else {
			System.out.println("配列を文字列型配列に変換することができませんでした。");
			return null;
		}
		
		return result;
	}
	
	// クラスからラッパークラスのインスタンスを生成するメソッド
	public Object parsePrimitive(Class<?> clazz, String str) {

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

	// 通知用パネルの設定
	private final void setNotifyPanel() {
		Font font = new Font("Arial", Font.PLAIN, 15);
		notifyPanel.setFont(font);
		notifyPanel.setEditable(false);
		notifyPanel.setBackground(this.getBackground());
		notifyPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
	}
	
	
}
