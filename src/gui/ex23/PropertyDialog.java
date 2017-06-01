package gui.ex23;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;

//設定したフォントサイズにあわせてウィンドウサイズを変更する。

public class PropertyDialog extends JDialog{

	//時計に関する情報を保持しているクラス
	//情報の参照、変更がプログラム＆UIから行うことができる
	private Parameter parameter;								//現在の設定値
	private Parameter nowParameter		= new Parameter();		//変更値
	
	private static final int MAXFONTSIZE 	= 	13;
	
	static final String[] color = {"BLACK","WHITE","RED","GREEN","BLUE","YELLOW","PINK","CYAN"};
	static final String[] size = {"50","100","150","200","250","300","350","400","450","500"};
	
	//使用できるフォントネームの取得
	private final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private final String fonts[] = ge.getAvailableFontFamilyNames();
	
	public PropertyDialog(Parameter parameter){
		super();
		JPanel base = new JPanel();
		base.setLayout(new BoxLayout(base, BoxLayout.Y_AXIS));
		this.parameter = parameter;
		base.add(new FontPanel());
		base.add(new FontSizePanel());
		base.add(new FontColorPanel());
		base.add(new BackgroundColorPanel());
		base.add(new ButtonPanel());
		this.add(base);
	}
	
	//フォントを変更するためのパネル→サンプルとして文字を変更する
	class FontPanel extends JPanel{
		
		public FontPanel() {
			
			JTextField title = new JTextField("Font");
			title.setEditable(false);
			title.setBackground(getBackground());
			title.setBorder(new EmptyBorder(1, 1, 1, 1));
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
			JComboBox<String> box = new JComboBox<String>(model);
			box.setPreferredSize(new Dimension(120, 20));
			for (String s : fonts) {
				model.addElement(s);
			}
			box.addActionListener(new FontActionListener(box, title));
			this.add(title);
			this.add(box);
			
		}
		
	}
	
	//フォントのサイズ変更パネル→何も表示しない
	class FontSizePanel extends JPanel{
		
		public FontSizePanel(){
			
			JTextField title = new JTextField("Font size");
			title.setEditable(false);
			title.setBackground(getBackground());
			title.setBorder(new EmptyBorder(1, 1, 1, 1));
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
			JComboBox<String> box = new JComboBox<String>(model);
			box.setPreferredSize(new Dimension(120, 20));
			for (String s : size) {
				model.addElement(s);
			}
			box.addActionListener(new FontSizeActionListener(box, title));
			this.add(title);
			this.add(box);
		}
		
	}
	
	//フォントのカラーを変更するパネル→サンプルを文字の色を表示する
	class FontColorPanel extends JPanel{

		public FontColorPanel() {

			JTextField title = new JTextField("Font color");
			title.setEditable(false);
			title.setBackground(getBackground());
			title.setBorder(new EmptyBorder(1, 1, 1, 1));
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
			JComboBox<String> box = new JComboBox<String>(model);
			box.setPreferredSize(new Dimension(120, 20));
			for (String s : color) {
				model.addElement(s);
			}
			box.addActionListener(new FontColorActionListener(box, title));
			this.add(title);
			this.add(box);
		}
		
	}
	
	//時計の背景色を変更するパネル→サンプルはカラーチップで表示
	class BackgroundColorPanel extends JPanel{
		
		public BackgroundColorPanel() {

			JTextField title = new JTextField("Background color");
			title.setEditable(false);
			title.setBackground(getBackground());
			title.setBorder(new EmptyBorder(1, 1, 1, 1));
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
			JComboBox<String> box = new JComboBox<String>(model);
			box.setPreferredSize(new Dimension(120, 20));
			for (String s : color) {
				model.addElement(s);
			}
			JPanel tip = new JPanel();
			tip.setPreferredSize(new Dimension(20, 20));
			tip.setBackground(Color.WHITE);
			BevelBorder border = new BevelBorder(BevelBorder.RAISED);
			tip.setBorder(border);			
			box.addActionListener(new BackgroundColorActionListener(box, title, tip));
			this.add(tip);
			this.add(title);
			this.add(box);
		}
		
	}
	
	//ダイアログのためのボタン
	class ButtonPanel extends JPanel{
		public ButtonPanel() {
			this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
			JButton set 	= new JButton("Set");
			set.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					parameter = nowParameter;
					dispose();
				}
			});
			JButton cancel 	= new JButton("Cancel");
			cancel.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					nowParameter = new Parameter();
					dispose();
				}
			});
			JButton init 	= new JButton("Initialize");
			init.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					nowParameter = new Parameter();
					parameter = new Parameter();
					dispose();
				}
			});
			
			this.add(set);
			this.add(cancel);
			this.add(init);
			
		}
	}
	
	//フォントが選択されたときのアクション　１.表示のフォントを変更する　2.現在のフォントを現在のパラメータに保存する
	class FontActionListener implements ActionListener{
		
		JComboBox<String> fontBox;
		JTextField fontname;
		public FontActionListener(JComboBox<String> fontBox, JTextField fontname) {
			this.fontBox = fontBox;
			this.fontname = fontname;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int ind = fontBox.getSelectedIndex();
			this.fontname.setFont(new Font(fonts[ind], Font.BOLD, 12));
			nowParameter.setFname(fonts[ind]);
			repaint();
			
		}
		
	}
	
	//フォントサイズが変更された時のアクション
	class FontSizeActionListener implements ActionListener{
		
		JComboBox<String> box;
		JTextField title;
		public FontSizeActionListener(JComboBox<String> box, JTextField title) {
			this.box = box;
			this.title = title;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int ind = box.getSelectedIndex();
			nowParameter.setFsize(Integer.parseInt(size[ind]));
			repaint();
			
		}
		
	}
	
	//フォントサイズが変更された時のアクション
	class FontColorActionListener implements ActionListener{
		
		JComboBox<String> box;
		JTextField title;
		public FontColorActionListener(JComboBox<String> box, JTextField title) {
			this.box = box;
			this.title = title;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			int ind = box.getSelectedIndex();
			Color col = getColorObject(color[ind]);
			nowParameter.setFcolor(col);
			title.setForeground(col);
			repaint();
			
		}
		
		//文字からColorオブジェクトへの変換
		public Color getColorObject(String color){
			if("BLACK".equals(color))		return Color.black;
			else if("WHITE".equals(color))	return Color.white;
			else if("RED".equals(color))	return Color.red;
			else if("GREEN".equals(color))	return Color.green;
			else if("BLUE".equals(color))	return Color.blue;
			else if("YELLOW".equals(color))	return Color.yellow;
			else if("PINK".equals(color))	return Color.pink;
			else if("CYAN".equals(color))	return Color.cyan;
			else{return Color.BLACK;}
		}
		
	}
	
	// フォントサイズが変更された時のアクション
	class BackgroundColorActionListener implements ActionListener {

		JComboBox<String> box;
		JTextField title;
		JPanel tip;

		public BackgroundColorActionListener(JComboBox<String> box, JTextField title, JPanel tip) {
			this.box = box;
			this.title = title;
			this.tip = tip;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			int ind = box.getSelectedIndex();
			Color col = getColorObject(color[ind]);
			nowParameter.setColor(col);
			tip.setBackground(col);
			repaint();

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
	
	public Parameter getParameter(){
		return parameter;
	}
	
}
