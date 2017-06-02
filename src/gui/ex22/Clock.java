package gui.ex22;

import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gui.ex11.ClockWindowAdapter;

/**
 * Swingを使用してデジタル時計を作成する
 * 
 * @author YoshikazuMurase
 *
 */
public class Clock extends JFrame implements Runnable {

	// クロックがもつ機能
	private ClockPanel cp; // 時計表示パネル
	private PropertyDialog dialog; // パラメータ設定ダイアログ
	private Parameter parameter; // パラメータ

	public static void main(String[] args) {
		Clock clock = new Clock();
		new Thread(clock).start();
	}

	// 時計の初期化処理
	public Clock() {
		// 初期パラメータの追加
		parameter = new Parameter();
		// メニューの追加
		JMenuBar menuBar = new JMenuBar();
		JMenu menuView = new JMenu("Property");
		JMenuItem item = new JMenuItem("Set parameter");
		item.addActionListener(new PropertyActionListener());
		menuView.add(item);
		menuBar.add(menuView);
//		setMenuBar(menuBar);
		setJMenuBar(menuBar);
		// 表示する時計のサイズ、削除処理の追加
		this.setSize((int) parameter.getWidth(), (int) parameter.getHeight());
		this.addWindowListener(new ClockWindowListener());
		this.cp = new ClockPanel(parameter);
		this.add(cp);
	}

	@Override
	public void run() {

		this.setVisible(true);

		// 時計の描画
		while (true) {
			try {
				cp.repaint(); // 描画処理
				Thread.sleep(50); // スリープ
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {

			}
		}

	}

	// プロパティダイアログを画面中央に配置するリスナー
	public class PropertyActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			int x = (int) (getX() + getWidth() / 5);
			int y = (int) (getY() + getHeight() / 5);
			int width = (int) (2 * getWidth() / 3);
			int height = (int) (2 * getHeight() / 3);
			PropertyDialog dialog = new PropertyDialog(parameter);
			dialog.setBounds(x, y, width, height);
			dialog.setModal(true);
			dialog.setVisible(true);
			setSize((int) dialog.getParameter().getWidth(), (int) dialog.getParameter().getHeight());
			cp.setParameter(dialog.getParameter());

		}

	}

}
