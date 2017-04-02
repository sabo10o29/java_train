package interpret;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import gui.ex14.ParameterWindow;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//動的なインスタンス生成はリフレクションを用いる
public class Interpret extends JFrame{

	private static final int WWidth = 800;
	private static final int HHeight = 600;
	private final StepMenuPanel sp = new StepMenuPanel();
	private final MainBasePanel mbp = new MainBasePanel();
	private final FlowButtonPanel fp = new FlowButtonPanel(sp,mbp);
	private final MainBasePanel mp = new MainBasePanel();
	
	private Class<?> targetClass = null;		//ターゲットのクラスを保管するフィールド
	
	
	
	public static void main(String[] args) {
		Interpret window = new Interpret();
		
	}
	
	Interpret(){
		iniWindow();
	}
	
	private void iniWindow(){
		
		//フレームに終了設定を追加
		this.addWindowListener(new MyWindowAdapter());
		//ウィンドウのサイズを設定
		this.setSize(WWidth, HHeight);
		//パネルをウィンドウに貼り付け
		this.add(sp, BorderLayout.WEST);			//ステップパネル
		this.add(fp,BorderLayout.PAGE_END);		//フローボタンパネル
		this.add(mbp);
		
		//ウィンドウの可視化
		this.setVisible(true);
	}
	
	class MyWindowAdapter extends WindowAdapter {
	    public void windowClosing(WindowEvent e) {
	    	System.exit(0);
	    }
	}

}
