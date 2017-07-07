package java8.ch01.ex05;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JComboBox;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

/**
 * 右クリック時にポップアップウィンドウを表示するクラス
 * 
 * @author YoshikazuMurase
 *
 */
public class PopupPropertyPanel extends JPopupMenu {

	// 時計に関する情報を保持しているクラス
	// 情報の参照、変更がプログラム＆UIから行うことができる
	private Parameter parameter; // 現在の設定値

	private static final int MAXFONTSIZE = 13;

	static final String[] color = { "BLACK", "WHITE", "RED", "GREEN", "BLUE", "YELLOW", "PINK", "CYAN" };
	static final String[] size = { "50", "100", "150", "200", "250", "300", "350", "400", "450", "500" };

	// 使用できるフォントネームの取得
	private final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private final String fonts[] = ge.getAvailableFontFamilyNames();

	public PopupPropertyPanel(Parameter parameter) {

		this.parameter = parameter;

		JMenu[] item = new JMenu[4];
		item[0] = new JMenu("Font");
		item[1] = new JMenu("Font Size");
		item[2] = new JMenu("Font Color");
		item[3] = new JMenu("Background Color");

		for (String name : fonts) {
			JMenuItem i = new JMenuItem(name);
			i.addActionListener(e -> {
				parameter.setFname(e.getActionCommand());
				repaint();
			});

			i.setFont(new Font(name, Font.PLAIN, 11));
			item[0].add(i);
		}

		for (String s : size) {
			JMenuItem i = new JMenuItem(s);
			i.addActionListener(e -> {
				parameter.setFsize(Integer.parseInt(e.getActionCommand()));
				repaint();
			});

			item[1].add(i);
		}

		for (String c : color) {
			JMenuItem i = new JMenuItem(c);
			i.addActionListener(e -> {
				parameter.setFcolor(getColorObject(e.getActionCommand()));
				repaint();
			});

			item[2].add(i);
			JMenuItem j = new JMenuItem(c);
			j.addActionListener(e -> {
				parameter.setColor(getColorObject(e.getActionCommand()));
				repaint();
			});

			item[3].add(j);
		}

		Arrays.stream(item).forEach(i -> this.add(i));

	}

	// 文字からColorオブジェクトへの変換
	public Color getColorObject(String color) {
		if ("BLACK".equals(color))
			return Color.black;
		else if ("WHITE".equals(color))
			return Color.white;
		else if ("RED".equals(color))
			return Color.red;
		else if ("GREEN".equals(color))
			return Color.green;
		else if ("BLUE".equals(color))
			return Color.blue;
		else if ("YELLOW".equals(color))
			return Color.yellow;
		else if ("PINK".equals(color))
			return Color.pink;
		else if ("CYAN".equals(color))
			return Color.cyan;
		else {
			return Color.BLACK;
		}
	}

}
