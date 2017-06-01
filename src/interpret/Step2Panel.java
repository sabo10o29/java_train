package interpret;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * クラスの選択パネル
 * 正準名を入力してクラスを選択する
 * @author YoshikazuMurase
 *
 */
public class Step2Panel extends StepBasePanel{
	
	private JPanel searchJPanel = new JPanel();		//検索パネル
	private JPanel numelementPanel = new JPanel();
	private Class<?> targetClass = null;			//仮保存用
	private JRadioButton check = new JRadioButton("配列");
	private JTextField efield = new JTextField("1", 2);		//配列の要素数用コンポーネント
	
	Step2Panel(HashMap<String, Object> parameter){

		super(parameter);
	}
	
	private void makeSearchJPanel(){
		
		searchJPanel.setSize(200, 200);
		numelementPanel.setSize(200,200);
		JTextField sname = new JTextField("クラス名",5);
		check = new JRadioButton("配列");
		sname.setEditable(false);
		sname.setBackground(getBackground());
		sname.setBorder(new EmptyBorder(1, 1, 1, 1));
		JTextField sfield = new JTextField("", 10);
		efield = new JTextField("1", 2);
		JButton sbutton = new JButton("Search");
		sbutton.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = sfield.getText();
				try {
					targetClass = Class.forName(className);		//クラス名からクラスの取得
					//対象がインターフェースまたは抽象クラスの場合には他のクラスにするように促す
					if(Modifier.isAbstract(targetClass.getModifiers())){			//対象が抽象クラスの場合
						notifyPanel.setText("\n\n" + SPACE + "対象は抽象クラスです。クラスを入力して下さい。");
						
					}else if(targetClass.isInterface()){	//対象がインターフェースの場合
						notifyPanel.setText("\n\n" + SPACE + "対象はインターフェースです。クラスを入力して下さい。");
						
					}else{									//それ以外のクラスの場合
						setParameter(ParameterConst.CLASS_NAME, targetClass); // クラス名を取得できた場合、パラメータに格納
						notifyPanel.setText("\n\n" + SPACE + "”" + targetClass.getSimpleName() + "” をロードしました。\n" + SPACE
								+ "このクラスで良ければ、”Next” ボタンを\n" + SPACE + "クリックしてください。");
						
					}
				} catch (ClassNotFoundException e1) {
					showErrorDialog(e1.getClass().getName());
					e1.printStackTrace();
				}
				
			}
		});
		
		check.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if(check.isSelected()){		//配列の場合の処理
					setParameter(ParameterConst.IS_ARRAY, new Boolean("true"));
					System.out.println("クラスの生成として配列が選択されました。");
				}else{						//配列ではない場合の処理
					setParameter(ParameterConst.IS_ARRAY, new Boolean("false"));
					System.out.println("単体のオブジェクトを生成します。");
				}
			}
		});
		
		setParameter(ParameterConst.IS_ARRAY, new Boolean("false"));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		//コンポーネントの追加
		searchJPanel.add(sname);
		searchJPanel.add(sfield);
		searchJPanel.add(sbutton);
		mainPanel.add(searchJPanel);
		numelementPanel.add(check);
		numelementPanel.add(efield);
		mainPanel.add(numelementPanel);
		
	}
	
	@Override
	public void init() {
		titlePanel.setText(TITLE_SPACE+"クラス名と個数を入力してください");
		descriPanel.setText(SPACE + "クラス名は正準名で入力してください\n"
				+ SPACE + "　　＊抽象クラス、インターフェースは対象外です。");
		descriPanel.setPreferredSize(new Dimension(10, 20));
		notifyPanel.setPreferredSize(new Dimension(10, 50));
		makeSearchJPanel();
	}

	@Override
	public boolean nextHandler() {
		
		setParameter(ParameterConst.NUM_ARRAY_ELEMENT, new Integer(1));
		try {
			int arraySize = 1;
			if ((Boolean)getParameter(ParameterConst.IS_ARRAY)) {
				String str = efield.getText();
				arraySize = Integer.parseInt(str);
				setParameter(ParameterConst.NUM_ARRAY_ELEMENT, new Integer(arraySize));
				if(arraySize == 0){
					showErrorDialog("配列を使用する場合には、０より大きい値を設定して下さい");
					return false;
				}
			}
		} catch (NumberFormatException ee) {
			showErrorDialog(ee.getClass().getName());
			System.out.println("配列の数を取得することができませんでした。");
			return false;
		}
		if (getParameter(ParameterConst.CLASS_NAME) != null) {
			return true;
		} else {
			System.out.println("クラス名がまだ入力されていません。");
			return false;
		}
	}

	@Override
	public void clear() {
		titlePanel.removeAll();
		notifyPanel.removeAll();
		notifyPanel.setText("");
		setParameter(ParameterConst.CLASS_NAME, null);
	}

}
