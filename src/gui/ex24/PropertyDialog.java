package gui.ex24;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.stream.IntStream;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * 時計に関して情報を保持しているクラス 参照・変更を行うことができる 課題：現在のパラメータが反映されない 課題：表示が整っていない
 * 
 * @author YoshikazuMurase
 *
 */
public class PropertyDialog extends JDialog {

	// 定数
	private static final int MAXFONTSIZE = 13;
	private static final String[] color = { "BLACK", "WHITE", "RED", "GREEN", "BLUE", "YELLOW", "PINK", "CYAN" };
	private static final String[] size = { "50", "100", "150", "200", "250", "300", "350", "400", "450", "500" };
	private static final JTextField[] labels = { new JTextField("Background color: "), new JTextField("Font color: "),
			new JTextField("Font size: "), new JTextField("Font: "), };
	private static final DefaultComboBoxModel<String>[] models = new DefaultComboBoxModel[4];
	private static final JComboBox<String>[] lists = new JComboBox[4];

	// パラメータ
	private Parameter parameter; // 現在の設定値
	private Parameter tmpParameter; // 変更値
	private GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private String fonts[] = ge.getAvailableFontFamilyNames();
	private JPanel base = new JPanel();

	public PropertyDialog(Parameter parameter) {
		super();
		this.parameter = parameter;
		tmpParameter = parameter.clone();
		this.setUndecorated(true);
		init();
		this.add(base);
	}

	// ベースパネルの初期化処理
	private void init() {
		Arrays.stream(labels).forEach(item -> {
			item.setEditable(false);
			item.setBackground(getBackground());
			item.setBorder(new EmptyBorder(1, 1, 1, 1));
		});
		IntStream.range(0, 4).forEach(i -> {
			models[i] = new DefaultComboBoxModel<String>();
			lists[i] = new JComboBox<String>(models[i]);
			lists[i].setPreferredSize(new Dimension(150, 20));
		});
		initBasePanel();
	}

	private void initBasePanel() {
		
		GridBagLayout layout = new GridBagLayout();
		base.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();

		// 背景色
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 2;
		gbc.gridy = 0;
		JPanel cc = new JPanel();
		cc.setSize(10, 10);
		cc.setBackground(parameter.getColor());
		gbc.anchor = GridBagConstraints.WEST;
		layout.setConstraints(cc, gbc);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.EAST;
		layout.setConstraints(labels[0], gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		gbc.anchor = GridBagConstraints.WEST;
		lists[0].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color c = getColorObject((String)lists[0].getSelectedItem());
				tmpParameter.setColor(c);
				cc.setBackground(c);
			}
		});
		Arrays.stream(color).forEach(i -> models[0].addElement(i));
		layout.setConstraints(lists[0], gbc);

		// 文字色
		gbc.gridx = 0;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.EAST;
		layout.setConstraints(labels[1], gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		gbc.anchor = GridBagConstraints.WEST;
		lists[1].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Color c = getColorObject((String)lists[1].getSelectedItem());
				tmpParameter.setFcolor(c);
				labels[1].setForeground(c);
			}
		});
		Arrays.stream(color).forEach(i -> models[1].addElement(i));
		layout.setConstraints(lists[1], gbc);

		// フォントサイズ
		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.EAST;
		layout.setConstraints(labels[2], gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		gbc.anchor = GridBagConstraints.WEST;
		lists[2].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				tmpParameter.setFsize(Double.parseDouble((String)lists[2].getSelectedItem()));
			}
		});
		Arrays.stream(size).forEach(i -> models[2].addElement(i));
		layout.setConstraints(lists[2], gbc);

		// フォント
		gbc.gridx = 0;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.EAST;
		layout.setConstraints(labels[3], gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		gbc.anchor = GridBagConstraints.WEST;
		lists[3].addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String fname = (String)lists[3].getSelectedItem();
				tmpParameter.setFname(fname);
				labels[3].setFont(new Font(fname, Font.PLAIN, 15));
			}
		});
		Arrays.stream(fonts).forEach(i -> models[3].addElement(i));
		layout.setConstraints(lists[3], gbc);

		// ボタンの実装
		gbc.insets = new Insets(50, 10, 10, 10);
		gbc.gridx = 2;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.SOUTH;
		JButton button1 = new JButton("Default");
		button1.addActionListener(new ButtonActionListener());
		layout.setConstraints(button1, gbc);

		gbc.gridx = 3;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.SOUTH;
		JButton button2 = new JButton("Set");
		button2.addActionListener(new ButtonActionListener());
		layout.setConstraints(button2, gbc);

		gbc.gridx = 4;
		gbc.gridy = 5;
		gbc.anchor = GridBagConstraints.SOUTH;
		JButton button3 = new JButton("Cancel");
		button3.addActionListener(new ButtonActionListener());
		layout.setConstraints(button3, gbc);
		
		base.add(cc);
		for(int i=0; i<4; i++){
			base.add(labels[i]);
			base.add(lists[i]);
		}
		base.add(button1);
		base.add(button2);
		base.add(button3);

	}

	// ボタンアクションリスナー
	class ButtonActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			// 押されたボタンに応じて実行切り替え
			System.out.println(e.getActionCommand());
			if (e.getActionCommand() == "Set") {
				writeParameter(tmpParameter);
				dispose();
			} else if (e.getActionCommand() == "Cancel") {
				dispose();
			} else if (e.getActionCommand() == "Default") {
				double x = parameter.getX();
				double y = parameter.getY();
				writeParameter(new Parameter());
				parameter.setX(x);
				parameter.setY(y);
				dispose();
			}

		}
	}

	/**
	 * 入力のパラメータをオリジナルのパラメータへ書き込む
	 * 
	 * @param param
	 */
	private void writeParameter(Parameter param) {

		parameter.setFname(param.getFname());
		parameter.setFcolor(param.getFcolor());
		parameter.setColor(param.getColor());
		parameter.setFsize(param.getFsize());
		parameter.setX(param.getX());
		parameter.setY(param.getY());

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
