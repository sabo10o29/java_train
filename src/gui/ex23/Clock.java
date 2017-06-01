package gui.ex23;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;

import gui.ex11.ClockWindowAdapter;

/**
 * Swingを使用してデジタル時計を作成する
 * @author YoshikazuMurase
 *
 */
public class Clock extends JFrame implements Runnable, MouseListener, MouseMotionListener{
	
	//クロックがもつ機能
	private ClockPanel cp;				//時計表示パネル
	private PropertyDialog dialog;		//パラメータ設定ダイアログ
	private Parameter parameter;		//パラメータ
	private double fpoint_x;			//クリック時の座標
	private double fpoint_y;			//
	
	public static void main(String[] args) {
		Clock clock = new Clock();
		new Thread(clock).start();
	}
	
	//時計の初期化処理
	public Clock() {
		//初期パラメータの追加
		parameter = new Parameter();
		//メニューの追加
		MenuBar menuBar = new MenuBar();
        Menu menuView = new Menu("Property");
        MenuItem item = new MenuItem("Set parameter");
        item.addActionListener(new PropertyActionListener());
        menuView.add(item);
        menuBar.add(menuView);
        setMenuBar(menuBar);
        //表示する時計のサイズ、削除処理の追加
        this.setSize((int)parameter.getWidth(), (int)parameter.getHeight());
		this.addWindowListener(new ClockWindowListener());
		this.cp = new ClockPanel(parameter);
		this.add(cp);
		addMouseListener(this);
		addMouseMotionListener(this);
		this.setUndecorated(true);
	}

	@Override
	public void run() {
		
		this.setVisible(true);
		
		//時計の描画
		while(true){
			try {
				this.setBounds((int)fpoint_x, (int)fpoint_y, (int)parameter.getWidth(), (int)parameter.getHeight());
				cp.repaint();		//描画処理
				Thread.sleep(500);	//スリープ
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				
			}
		}
		
		
	}
	
	//プロパティダイアログを画面中央に配置するリスナー
	public class PropertyActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			int x = (int)(getX() + getWidth()/5);
			int y = (int)(getY() + getHeight()/5);
			int width = (int)(2*getWidth()/3);
			int height = (int)(2*getHeight()/3);
			PropertyDialog dialog = new PropertyDialog(parameter);
			dialog.setBounds(x, y, width, height);
			dialog.setModal(true);
			dialog.setVisible(true);
			setSize((int)dialog.getParameter().getWidth(), (int)dialog.getParameter().getHeight());
			cp.setParameter(dialog.getParameter());
			
		}
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// クリック時の座標と現在の座標から、ウィンドウの表示位置を決定
		// 現在の位置 - クリック時の座標
		double x = MouseInfo.getPointerInfo().getLocation().getX() - this.fpoint_x;
		double y = MouseInfo.getPointerInfo().getLocation().getY() - this.fpoint_y;
		// ウィンドウにマウスの座標をロケーションとして設定
		this.setLocation((int) x, (int) y);

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO 自動生成されたメソッド・スタブ
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int btn = e.getButton(); // クリックの状態取得
		Point point = e.getPoint(); // クリックした際の座標取得

		if (btn == MouseEvent.BUTTON1) {
			// System.out.println("左ボタンクリック");
		} else if (btn == MouseEvent.BUTTON2) {
			// System.out.println("中ボタンクリック");
		} else if (btn == MouseEvent.BUTTON3) {
			// ポップアップを表示
//			this.popup.show(e.getComponent(), point.x, point.y);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// マウスが押されたときの場所を保存
		this.fpoint_x = e.getX();
		this.fpoint_y = e.getY();

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
