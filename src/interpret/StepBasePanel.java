package interpret;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public abstract class StepBasePanel extends JPanel implements ErrorDialog{
	
	private static final int WWidth = 650;
	private static final int HHeight = 550;
	private final Map<String, Object> parameter;
	
	protected JTextField titlePanel 	= new JTextField();		//タイトル
	protected JTextArea descriPanel		= new JTextArea();		//説明
	protected JPanel mainPanel 			= new JPanel();			//メイン
	protected JTextArea notifyPanel 	= new JTextArea();		//通知
	
	public static final String TITLE_SPACE = "    ";
	public static final String SPACE = "          ";
	
	public StepBasePanel(HashMap<String, Object> parameter) {
		
		this.parameter = parameter;
		this.setMaximumSize(new Dimension(WWidth, HHeight));
		
		setTitle();
		setMainPanel();
		setNotifyPanel();
		setDescri();
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		add(titlePanel);
		add(descriPanel);
		add(mainPanel);
		add(notifyPanel);
		
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
	
	//ゲッターセッター
	public final void setParameter(String key, Object value){
		parameter.put(key, value);
	}
	public final Object getParameter(String key){
		return parameter.get(key);
	}
	
//	public final void setFlag(boolean flag){
//		this.flag = flag;
//	}
//	public final boolean getFlag(){
//		return this.flag;
//	}
	
	public void setNotifyText(String str){
		notifyPanel.setText(str);
	}
	
	@Override
	public void showErrorDialog(String message) {
		JLabel label = new JLabel("Error!: " + message);
	    label.setForeground(Color.RED);
	    JOptionPane.showMessageDialog(this, label);
	}
	
	public abstract void init();			//前のパネルの設定をもとにした初期化処理
	public abstract boolean nextHandler();	//次のステップへいく際の処理: 処理が完了した場合にtrueを返す
	public abstract void clear();			//戻る際のクリア処理

}
