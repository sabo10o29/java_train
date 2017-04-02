package interpret;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public abstract class StepBasePanel extends JPanel{
	
	private boolean flag = false;
	private final Map<String, Object> parameter = new HashMap<String, Object>();
	private Map<String, Object> bfParameter = new HashMap<String, Object>();
//	private final Map<String, Object> afParameter = new HashMap<String, Object>(); 
	
	protected JTextField titlePanel 	= new JTextField();
	protected JTextArea descriPanel	= new JTextArea();
	protected JPanel mainPanel 			= new JPanel();
	protected JTextArea notifyPanel 	= new JTextArea();
	
	public static final String TITLE_SPACE = "    ";
	public static final String SPACE = "          ";
	
	public StepBasePanel() {
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
	}
	
	private final void setNotifyPanel(){
		Font font = new Font("Arial", Font.PLAIN, 15);
		notifyPanel.setFont(font);
		notifyPanel.setEditable(false);
		notifyPanel.setBackground(this.getBackground());
		notifyPanel.setBorder(new EmptyBorder(2, 2, 2, 2));
	}
	
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
	
	public abstract void init();			//前のパネルの設定をもとにした初期化処理
	public abstract void nextHandler();		//次のステップへいく際の処理
	public abstract void clear();			//戻る際のクリア処理

}
