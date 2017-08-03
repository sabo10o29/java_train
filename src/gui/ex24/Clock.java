package gui.ex24;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Swingを使用してデジタル時計を作成する
 * 課題：プロパティダイアログにグリッドレイアウトを使用する
 * きれいに配置されるように表示する
 * @author YoshikazuMurase
 *
 */
public class Clock extends JFrame implements Runnable, MouseListener, MouseMotionListener {

	// クロックがもつ機能
	private ClockPanel cp; // 時計表示パネル
	private PrefsParameter pref; // パラメータ保存用インスタンス
	private Parameter parameter; // パラメータ:一回の起動でパラメータは一回のみ生成される
	private PopupPropertyPanel property;
	private double fpoint_x; // クリック時の座標
	private double fpoint_y; //

	public static void main(String[] args) {
		Clock clock = new Clock();
		new Thread(clock).start();
	}

	// 時計の初期化処理
	public Clock() {
		// 初期化処理
		Runtime.getRuntime().addShutdownHook(new Shutdown()); // シャットダウンフックを登録する
		// 初期パラメータの追加
		// セーブされているパラメータをロードする→ロードできない場合には新しくインスタンスを生成する。
		pref = new PrefsParameter();
		try {
			// pref.clear();
			parameter = pref.load();
		} catch (ClassNotFoundException e) {
			
		} catch (IOException e) {
			
		}

		if (parameter == null)
			parameter = new Parameter();

		property = new PopupPropertyPanel(parameter);
		setupDialog();

		this.setBounds((int) parameter.getX(), (int) parameter.getY(), (int) parameter.getWidth(),
				(int) parameter.getHeight());
		this.addWindowListener(new ClockWindowListener());
		this.cp = new ClockPanel(parameter);
		this.add(cp);
		addMouseListener(this);
		addMouseMotionListener(this);
		this.setUndecorated(true);
	}

	public void setupDialog() {
		// メニューの追加
		JMenuBar menuBar = new JMenuBar();
		JMenu menuView = new JMenu("Property");
		JMenuItem item = new JMenuItem("Set parameter");
		item.addActionListener(new PropertyActionListener());
		menuView.add(item);
		menuBar.add(menuView);
		setJMenuBar(menuBar);
	}

	@Override
	public void run() {

		this.setVisible(true);

		// 時計の描画
		while (true) {
			try {
				setSize((int) parameter.getWidth(), (int) parameter.getHeight()); // フォントサイズの変更があった場合にウィンドウサイズを変更するための処理
				cp.repaint(); // 描画処理
				Thread.sleep(500); // スリープ
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {

			}
		}

	}

	/**
	 * アプリを終了する際に現在の設定を保存する処理
	 * 
	 * @author YoshikazuMurase
	 *
	 */
	class Shutdown extends Thread {
		public void run() {
			// ここに、アプリケーション終了時に実施する処理を追加します
			try {
				parameter.setX(getX());
				parameter.setY(getY());
				pref.save(parameter);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("Closing window");
		}
	}
	
	/**
	 * プロパティーダイアログを表示するリスナー
	 * @author YoshikazuMurase
	 *
	 */
	public class PropertyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int x = (int) (getX() + getWidth() / 5);
			int y = (int) (getY() + getHeight() / 5);
//			int width = (int) (2 * getWidth() / 3);
//			int height = (int) (2 * getHeight() / 3);
			PropertyDialog dialog = new PropertyDialog(parameter);
			dialog.setBounds(x, y, 700, 250);
			dialog.setModal(true);
			dialog.setVisible(true);
			repaint();
//			parameter = dialog.getParameter();
//			setSize((int) dialog.getParameter().getWidth(), (int) dialog.getParameter().getHeight());
//			cp.setParameter(dialog.getParameter());

		}

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// 移動量の計算
		double x = e.getX() - this.fpoint_x;
		double y = e.getY() - this.fpoint_y;
		this.setLocation((int) x + this.getX(), (int) y + this.getY());

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
//			this.property.show(this, e.getX(), e.getY());
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
