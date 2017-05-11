package interpret;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import gui.ex14.ParameterWindow;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.LayoutManager;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

//フィールドの参照型の設定
//複数クラスを選択できるよにする
//例外を表示していない
//型は合っているが範囲が異なる場合の例外処理ができていない。
public class Interpret extends JFrame{

	private static final int WWidth = 800;
	private static final int HHeight = 600;
	private final JTabbedPane tab = new JTabbedPane(JTabbedPane.RIGHT);
	private int classCount = 1;
//	private final StepMenuPanel sp = new StepMenuPanel();
//	private final MainBasePanel mbp = new MainBasePanel();
//	private final FlowButtonPanel fp = new FlowButtonPanel(sp,mbp);
//	private final MainBasePanel mp = new MainBasePanel();
	
	private Class<?> targetClass = null;		//ターゲットのクラスを保管するフィールド
	
	
	public static void main(String[] args) {
		Interpret window = new Interpret();
		
	}
	
	public Interpret(){
		iniWindow();
	}
	
	private void iniWindow(){

		MenuBar menuBar = new MenuBar();
        Menu menuView = new Menu("Class");
        menuBar.add(menuView);
        MenuItem item = new MenuItem("Add new class");
        item.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				++classCount;
				createTab("Class "+classCount);
				System.out.println("新しくクラス生成ウィンドウを作成しました。");
			}
		});
        menuView.add(item);
        setMenuBar(menuBar);
		
		//フレームに終了設定を追加
		this.addWindowListener(new MyWindowAdapter());
		//ウィンドウのサイズを設定
		this.setSize(WWidth, HHeight);
		
		for(int i= 1; i<2; i++){
			createTab("Class "+i);
		}
		//ウィンドウの可視化
		this.add(tab);
		this.setVisible(true);
	}
	
	public void createTab(String name){
		//ベースとなるパネルの生成
		JPanel base = new JPanel();
		base.setLayout(new BoxLayout(base, BoxLayout.X_AXIS));
		JPanel subbase = new JPanel();
		subbase.setLayout(new BoxLayout(subbase, BoxLayout.Y_AXIS));
		
		//内部パネルの生成
		StepMenuPanel sp = new StepMenuPanel();
		MainBasePanel mbp = new MainBasePanel();
		FlowButtonPanel fp = new FlowButtonPanel(sp,mbp);
		MainBasePanel mp = new MainBasePanel();
		
		base.add(sp, BorderLayout.WEST);		//ステップパネル
		subbase.add(mbp,BorderLayout.NORTH);	//メインパネル
		subbase.add(fp,BorderLayout.SOUTH);		//フローボタンパネル
		base.add(subbase);
		tab.addTab(name, base);
		this.repaint();
	}
	
	class MyWindowAdapter extends WindowAdapter {
	    public void windowClosing(WindowEvent e) {
	    	System.exit(0);
	    }
	}

}
