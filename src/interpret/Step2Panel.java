package interpret;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Modifier;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//Step3にクラス名を渡す
public class Step2Panel extends StepBasePanel{
	
	private JPanel searchJPanel = new JPanel();		//検索パネル
	private Class<?> targetClass = null;			//狩り保存用
	
	Step2Panel(){
		titlePanel.setText(TITLE_SPACE+"クラス名を入力してください");
		descriPanel.setText(SPACE + "クラス名は正準名で入力してください\n"
				+ SPACE + "　　＊抽象クラス、インターフェースは対象外です。");
		descriPanel.setPreferredSize(new Dimension(10, 20));
		notifyPanel.setPreferredSize(new Dimension(10, 50));
		makeSearchJPanel();
	}
	
	private void makeSearchJPanel(){
		
		//検索窓の設定
		searchJPanel.setSize(300, 200);
		searchJPanel.setLayout(new GridLayout(1, 2));
		JTextField field = new JTextField("", 10);
		JButton fb = new JButton("Search");
		fb.addActionListener( new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String className = field.getText();
				try {
					targetClass = Class.forName(className);		//クラス名からクラスの取得
					//対象がインターフェースまたは抽象クラスの場合には他のクラスにするように促す
					if(Modifier.isAbstract(targetClass.getModifiers())){			//対象が抽象クラスの場合
						notifyPanel.setText("\n\n" + SPACE + "対象は抽象クラスです。クラスを入力して下さい。");
						setFlag(false);	
						
					}else if(targetClass.isInterface()){	//対象がインターフェースの場合
						notifyPanel.setText("\n\n" + SPACE + "対象はインターフェースです。クラスを入力して下さい。");
						setFlag(false);
						
					}else{									//それ以外のクラスの場合
						setParameter(ParameterConst.CLASS_NAME, targetClass); // クラス名を取得できた場合、パラメータに格納
						notifyPanel.setText("\n\n" + SPACE + "”" + targetClass.getSimpleName() + "” をロードしました。\n" + SPACE
								+ "このクラスで良ければ、”Next” ボタンを\n" + SPACE + "クリックしてください。");
						setFlag(true);
						
					}
				} catch (ClassNotFoundException e1) {
					notifyPanel.setText("\n\n" + SPACE + "クラスをロードできませんでした。\n"
							+ SPACE + "クラス名を再度入力してください。");
					setFlag(false);
//					repaint();
				}
				
			}
		});
		searchJPanel.add(field);
		searchJPanel.add(fb);
		mainPanel.add(searchJPanel);
		
	}

	@Override
	public void init() {
		//前のステップが存在しない　→　前のパラメータが存在しないため、初期化処理は行わない
		setFlag(false);
		notifyPanel.setText("");
	}

	@Override
	public void nextHandler() {
		// 検索ボタンの処理でパラメータの設定を行っているため必要ない
		
	}

	@Override
	public void clear() {
		titlePanel.removeAll();
//		descriPanel.removeAll();
//		mainPanel.removeAll();
		notifyPanel.removeAll();
		notifyPanel.setText("");
	}
	
	

}
