package interpret;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import interpret.FieldsPanel.EditObjectButtonListener;
import interpret.FieldsPanel.ModifyFieldListener;
import interpret.FieldsPanel.MyButtonListener;

public class MethodsPanel extends JPanel {

	Object instance 			= null;
	Field[] fields 			= null;
	Method[] methods 		= null;
	Object[] values 			= null;
	Object[][] methodsarg		= null;		
	JTextField notifyPanel 	= new JTextField();
	JTextField resultPanel  = new JTextField();
	

	JList<Object> methodlist = null;

	int nowind = 0;

	public MethodsPanel(Object instance){
		
		//初期化処理
		this.instance = instance;	//インスタンス
		this.methods = instance.getClass().getMethods(); //メソッド一覧
		this.methodlist = new JList<Object>(this.methods);
		this.methodsarg = new Object[this.methods.length][];
		
		JPanel base = new JPanel();		//ベースパネル
		JPanel modify = new JPanel();	//値修正パネル
		JScrollPane sp = new JScrollPane();	//スクロールパネル
		setNotifyPanel();
		
	    sp.getViewport().setView(methodlist);
	    sp.setPreferredSize(new Dimension(500, 100));
		
		//メソッドのパラメータを設定するリスナー
		JButton edit = new JButton("Edit");
		edit.addMouseListener(new EditMethodListener());
		
		//メソッドを呼び出すリスナー
		JButton invoke = new JButton("Invoke method");
		invoke.addActionListener(new InvokeMethodListener());
		
		base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
		modify.setLayout(new BoxLayout(modify, BoxLayout.X_AXIS));
		
		modify.add(edit);
		modify.add(invoke);
		modify.add(resultPanel);
		base.add(sp);
		base.add(modify);
		base.add(notifyPanel);
		
		this.add(base);
		
	}
	
	//メソッドのパラメータ設定ボタン
	class EditMethodListener implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			nowind = methodlist.getSelectedIndex();
			Method nowmethod = methods[nowind];
			Type[] types = nowmethod.getGenericParameterTypes();
			SubArgListDialog dialog = new SubArgListDialog(types);
			Point point = e.getLocationOnScreen();
			dialog.setBounds(point.x, point.y, 500, 250);
			dialog.setVisible(true);
			methodsarg[nowind] = dialog.getPram();
			System.out.println("メソッドの実行に必要なパラメータの設定が完了しました。");
			
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
	
	//メソッド実行ボタン
	class InvokeMethodListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			nowind = methodlist.getSelectedIndex();
			Method nowmethod = methods[nowind];
			Type[] types = nowmethod.getGenericParameterTypes();
			//メソッドの実行
			Object result = null;
			try {
				if (types.length == 0) {
					result = nowmethod.invoke(instance, null);
				} else {
					result = nowmethod.invoke(instance, methodsarg[nowind]);
				}
				System.out.println("メソッドを実行しました！");
				//結果の表示
				if(result!=null)resultPanel.setText(result.toString());
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e1) {
				System.out.println("メソッドを実行できませんでした。");
				notifyPanel.setText("メソッドを実行することができませんでした。\n"
						+ e1.getMessage());
				e1.printStackTrace();
			}

		}
		
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

	private final void setNotifyPanel() {
		Font font = new Font("Arial", Font.PLAIN, 15);
		notifyPanel.setFont(font);
		notifyPanel.setEditable(false);
		notifyPanel.setBackground(this.getBackground());
		notifyPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
	}
	
}
