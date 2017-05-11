package interpret;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public abstract class StepBasePanel extends JPanel{
	
	public static ArrayList<ShareArgument> shareList = new ArrayList<>();
	private boolean flag = false;
	private final Map<String, Object> parameter = new HashMap<String, Object>();
	private Map<String, Object> bfParameter = new HashMap<String, Object>();
//	private final Map<String, Object> afParameter = new HashMap<String, Object>(); 
	
	protected JTextField titlePanel 	= new JTextField();		//タイトル
	protected JTextArea descriPanel		= new JTextArea();		//説明
	protected JPanel mainPanel 			= new JPanel();			//メイン
	protected JTextArea notifyPanel 	= new JTextArea();		//通知
//	protected JPanel stepPanel			= new JPanel();			//遷移ボタン
	
	public static final String TITLE_SPACE = "    ";
	public static final String SPACE = "          ";
	
	public StepBasePanel() {
		setTitle();
		setMainPanel();
		setNotifyPanel();
		setDescri();
//		setStepPanel();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(titlePanel);
		add(descriPanel);
		add(mainPanel);
		add(notifyPanel);
//		add(stepPanel);
	}
	
	private final void setTitle(){
		Font font = new Font("Arial", Font.BOLD, 20);
		titlePanel.setFont(font);
		titlePanel.setEditable(false);
		titlePanel.setBackground(this.getBackground());
		titlePanel.setBorder(new EmptyBorder(2, 2, 2, 2));
		titlePanel.setPreferredSize(new Dimension(10, 20));
	}
	
	private final void setDescri(){
		Font font = new Font("Arial", Font.PLAIN, 15);
		descriPanel.setFont(font);
		descriPanel.setEditable(false);
		descriPanel.setBackground(this.getBackground());
		descriPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
	}
	
	private final void setMainPanel(){
		mainPanel.setBackground(this.getBackground());
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
	}
	
	private final void setNotifyPanel(){
		Font font = new Font("Arial", Font.PLAIN, 15);
		notifyPanel.setFont(font);
		notifyPanel.setEditable(false);
		notifyPanel.setBackground(this.getBackground());
		notifyPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
	}
	
//	private final void setStepPanel(){
//		stepPanel.setBackground(this.getBackground());
//		stepPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
//		FlowLayout layout = new FlowLayout();
//		layout.setAlignment(FlowLayout.RIGHT);
//		stepPanel.setLayout(layout);
//		JButton next = new JButton("Next");
//		next.addActionListener(getNextActionListener());
//		JButton back = new JButton("Back");
//		back.addActionListener(getBackActionListener());
//		stepPanel.add(next);
//		stepPanel.add(back);
//	}
	
	public void setBfParameter(Map<String, Object> parameter){
		this.bfParameter = parameter;
//		this.repaint();s
	}
	public Map<String, Object> getBfParameter(){
		return bfParameter;
	}
	
	public Map<String, Object> getMap(){
		return parameter;
	}
	
	//ゲッターセッター
	public final void setParameter(String key, Object value){
		parameter.put(key, value);
	}
	public final Object getParameter(String key){
		return parameter.get(key);
	}
	
	public final void setFlag(boolean flag){
		this.flag = flag;
	}
	public final boolean getFlag(){
		return this.flag;
	}
	
	public void setNotifyText(String str){
		notifyPanel.setText(str);
	}
	
	public abstract void init();			//前のパネルの設定をもとにした初期化処理
	public abstract void nextHandler();		//次のステップへいく際の処理
	public abstract void clear();			//戻る際のクリア処理
	public abstract boolean check();		//次のパネルへ遷移しても良いかを判断するメソッド
//	public abstract ActionListener getNextActionListener();		//次のパネルへ遷移するためのリスナー
//	public abstract ActionListener getBackActionListener();		//前のパネルへ遷移するためのリスナー

}
