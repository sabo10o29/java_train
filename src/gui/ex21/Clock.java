package gui.ex21;

import javax.swing.JFrame;

import gui.ex11.ClockWindowAdapter;

/**
 * Swingを使用してデジタル時計を作成する
 * @author YoshikazuMurase
 *
 */
public class Clock extends JFrame implements Runnable{

	private static final int WIDTH = 600;
	private static final int HEIGHT = 300;
	
	
	public static void main(String[] args) {
		Clock clock = new Clock();
		new Thread(clock).start();
	}

	@Override
	public void run() {
		//フレームの初期化
		this.setSize(WIDTH, HEIGHT);
		this.addWindowListener(new ClockWindowListener());
		ClockPanel cp = new ClockPanel(WIDTH, HEIGHT);
		this.add(cp);
		this.setVisible(true);
		
		//時計の描画
		while(true){
			try {
				cp.repaint();		//描画処理
				Thread.sleep(50);	//スリープ
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				
			}
		}
		
		
	}
	
	

}
