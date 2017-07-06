package jpl8.ch01.ex05;

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
 * 
 * @author YoshikazuMurase
 *
 */
public class Clock extends JFrame implements Runnable, MouseListener, MouseMotionListener {

	// クロックがもつ機能
	private ClockPanel cp; // 時計表示パネル
	private Parameter parameter; // パラメータ
	private PopupPropertyPanel property;
	private double fpoint_x; // クリック時の座標
	private double fpoint_y; //

	public static void main(String[] args) {
		Clock clock = new Clock();
		new Thread(clock).start();
	}

	// 時計の初期化処理
	public Clock() {
		// 初期パラメータの追加
		parameter = new Parameter();
		property = new PopupPropertyPanel(parameter);

		this.setSize((int) parameter.getWidth(), (int) parameter.getHeight());
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
			System.out.println("右クリック");
			this.property.show(this, e.getX(), e.getY());
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
