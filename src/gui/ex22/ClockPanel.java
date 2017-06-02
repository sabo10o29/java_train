package gui.ex22;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.JPanel;

/**
 * 時計のベースとなるパネル。更新を行うことで、設定に合わせた描画を行う。
 * 
 * @author YoshikazuMurase
 *
 */
public class ClockPanel extends JPanel {

	private Image buf;
	private String[] strTime = CurrentStringTime.getFullTime();
	private Parameter parameter;

	public ClockPanel(Parameter parameter) {
		this.parameter = parameter;
	}

	// 再描画処理：現在の値によってサイズや色、大きさなどの変更を行い更新する：グラフィックスクラスを使用して秒が処理を行
	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		setBackground(parameter.getColor());
		String str = createStringTime();
		// 画面のサイズをバッファに設定
		// フォントの大きさを設定（ウィンドウ幅の1/5を基準に設定）
		int fontsize = (int) (parameter.getFsize());
		parameter.setFont(new Font(parameter.getFname(), Font.BOLD, fontsize));
		g2.setFont(parameter.getFont()); // 描画処理//
		// ベースライン情報の取得
		FontMetrics fm = g2.getFontMetrics();
		Rectangle rectText = fm.getStringBounds(str, g2).getBounds();
		// 表示位置の計算
		int _height = (int) (this.getSize().getHeight() / 2) + (fm.getMaxAscent() / 2);
		int _width = (int) (this.getSize().getWidth() - rectText.width) / 2;
		// 描画
		g2.setColor(parameter.getFcolor()); // 描画処理//
		g2.drawString(str, _width, _height); // 描画処理//
		g2.setColor(parameter.getColor()); // 描画設定//
	}

	// 表示する時刻を作成するためのメソッド：ここの変更によって表示形式を変更する
	public String createStringTime() {
		String str;
		strTime = CurrentStringTime.getFullTime();
		str = strTime[5] + strTime[4] + ":" + strTime[3] + strTime[2] + ":" + strTime[1] + strTime[0];
		return str;
	}

	// 描画に用いるパラメータの更新
	public void setParameter(Parameter parameter) {
		this.parameter = parameter;
	}

}
