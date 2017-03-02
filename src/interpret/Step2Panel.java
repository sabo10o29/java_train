package interpret;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Method;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

//Step3にクラス名を渡す
public class Step2Panel extends StepBasePanel{
	
	private String classname = null; 
	private JPanel searchJPanel = new JPanel();
	
	
	Step2Panel(){
		makeSearchJPanel();
		
	}
	
	private void makeSearchJPanel(){
		Class frame = Frame.class;
		Method[] test = frame.getMethods();
		for(int i = 0; i<test.length; i++){
			System.out.println(test[i].toString());
		}
		
		searchJPanel.setSize(300, 200);
		
		//
		searchJPanel.setLayout(new GridLayout(1, 3));
		//
		JLabel searchlabel = new JLabel("Class Name: ");
		JTextField field = new JTextField("Class Name", 20);
		//field.addKeyListener();
		
		JButton fb = new JButton("Search");
		searchJPanel.add(searchlabel);
		searchJPanel.add(field);
		searchJPanel.add(fb);
		
		this.add(searchJPanel,BorderLayout.CENTER);
	}
	

	public boolean checkNullClass(){
		if(classname == null){
			return false;
		}else{
			return true;
		}
	}
	
	@Override
	public void paintPanel(Graphics2D g2) {
		Font font = new Font("Arial", Font.BOLD, 20);
		g2.setFont(font);
		g2.drawString("クラスを選択して下さい。", 30, 60);
		
	}
	
	class ChooseMethodKeyListener implements KeyListener{
		
		@Override
		public void keyTyped(KeyEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
		
		@Override
		public void keyReleased(KeyEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
		
		@Override
		public void keyPressed(KeyEvent e) {
			// TODO 自動生成されたメソッド・スタブ
			
		}
	}
	

}
