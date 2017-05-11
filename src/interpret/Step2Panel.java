package interpret;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Modifier;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

//Step3にクラス名を渡す
public class Step2Panel extends StepBasePanel{
	
	private JPanel searchJPanel = new JPanel();		//検索パネル
//	private JPanel numClassJPanel = new JPanel();	//クラスの個数入力パネル
	private Class<?> targetClass = null;			//仮保存用
//	private int numClass = 0;						//クラスの個数保存用変数
	
	Step2Panel(){
		titlePanel.setText(TITLE_SPACE+"クラス名と個数を入力してください");
		descriPanel.setText(SPACE + "クラス名は正準名で入力してください\n"
				+ SPACE + "　　＊抽象クラス、インターフェースは対象外です。");
		descriPanel.setPreferredSize(new Dimension(10, 20));
		notifyPanel.setPreferredSize(new Dimension(10, 50));
		makeSearchJPanel();
	}
	
	private void makeSearchJPanel(){
		
		//検索窓の設定
		searchJPanel.setSize(200, 200);
		JTextField sname = new JTextField("クラス名",10);
		sname.setEditable(false);
		sname.setBackground(getBackground());
		sname.setBorder(new EmptyBorder(1, 1, 1, 1));
		JTextField sfield = new JTextField("", 10);
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
				}
				
			}
		});
		
		//クラスの個数を指定する
//		numClassJPanel.setSize(200, 200);
//		JTextField numfield = new JTextField("1", 10);
//		JTextField numname = new JTextField("個数",10);
//		numname.setEditable(false);
//		numname.setBackground(getBackground());
//		numname.setBorder(new EmptyBorder(1, 1, 1, 1));
//		JButton numbutton = new JButton("Set");
//		numbutton.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				try{
//					numClass = Integer.parseInt(numfield.getText());
//				}catch(NumberFormatException ee){
//					System.out.println("整数ではない値が入力されました。");
//					notifyPanel.setText("\n\n" + SPACE + "整数を入力して下さい\n");
//				}
//				
//				
//			}
//		});
		
		//コンポーネントの追加
		searchJPanel.add(sname);
		searchJPanel.add(sfield);
		searchJPanel.add(sbutton);
//		numClassJPanel.add(numname);
//		numClassJPanel.add(numfield);
//		numClassJPanel.add(numbutton);
//		mainPanel.add(numClassJPanel);
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
		notifyPanel.removeAll();
		notifyPanel.setText("");
	}

	@Override
	public boolean check() {
		//クラスとクラスの個数が正しく設定されていればtrueを返す
		if(getFlag()){
			return true;
		}else{
			return false;
		}
	}

}
