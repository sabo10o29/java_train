package gui.ex21;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
/**
 * 時計のベースとなるパネル。更新を行うことで、設定に合わせた描画を行う。
 * @author YoshikazuMurase
 *
 */
public class ClockPanel extends JPanel{
	
	private int panelWidth 	= 600;					//初期ウィンドウサイズ
	private int panelHeight 	= 300;				//
	private int maxSize 	= 13;					//
	private Font font = new Font(Font.DIALOG, Font.BOLD, 30);		//時計用フォントクラス
	private String 	font_name 	= "Arial";			//初期フォント
	private double 	font_multi 	= 1;				//初期フォントサイズ（倍率）
	private Color 	font_color 	= Color.black;		//初期フォントカラー
	private Color   back_color  = this.getBackground();
	private Image buf;
	private String[] strTime = CurrentStringTime.getFullTime();
	
	
	public ClockPanel(int width, int height) {
		
		this.panelHeight = height;	//フレームサイズの初期値
		this.panelWidth = width;	//
		
	}
	
	//再描画処理：現在の値によってサイズや色、大きさなどの変更を行い更新する：グラフィックスクラスを使用して秒が処理を行う
	public void paintComponent(Graphics g){
		String str = createStringTime();
		//画面のサイズをバッファに設定
		this.buf = createImage(getSize().width, getSize().height);
		// バッファのグラフィックスを取り出し、そこに描画を書き込む
		Graphics buf_graphics = this.buf.getGraphics();
		// フォントの大きさを設定（ウィンドウ幅の1/5を基準に設定）
		int fontsize = (int) (this.font_multi * this.getSize().width / 5);
		this.font = new Font(this.font_name, Font.BOLD, fontsize);
		buf_graphics.setFont(this.font); // 描画処理//
		// ベースライン情報の取得
		FontMetrics fm = buf_graphics.getFontMetrics();
		Rectangle rectText = fm.getStringBounds(str, buf_graphics).getBounds();
		// 表示位置の計算
		int _height = (int) (this.getSize().getHeight() / 2) + (fm.getMaxAscent() / 2);
		int _width = (int) (this.getSize().getWidth() - rectText.width) / 2;
		// 描画
		buf_graphics.setColor(this.font_color); // 描画処理//
		buf_graphics.drawString(str, _width, _height); // 描画処理//
		g.drawImage(this.buf, 0, 0, this); // バッファをグラフィックスに書き込む
	}
	
	//表示する時刻を作成するためのメソッド：ここの変更によって表示形式を変更する
	public String createStringTime(){
		String str; 
		strTime = CurrentStringTime.getFullTime();
		str = strTime[5]+strTime[4]+":"+strTime[3]+strTime[2]+":"+strTime[1]+strTime[0];
		return str;
	}
	
}
