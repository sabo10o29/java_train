package gui.ex14;

import java.awt.Button;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Label;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ParameterWindow extends Window{

	private static final int WWIDTH 		= 	800;
	private static final int WHEIGHT 		= 	300;
	private static final int MAXFONTSIZE 	= 	13;
	
	static final String[] color = {"BLACK","WHITE","RED","GREEN","BLUE","YELLOW","PINK","CYAN"};
	
	private Parameter ini_parameter 	= new Parameter();		//初期値（初期化に使用）
	private Parameter now_parameter		= new Parameter();		//現在の設定値（設定のキャンセル時に使用）
	private Parameter edit_parameter	= new Parameter();		//設定後の値（OK時に使用）
	
	//使用できるフォントネームの取得
	private final GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private final String fonts[] = ge.getAvailableFontFamilyNames();


	public ParameterWindow(){
		super(new Frame());
		setParameterWindow();

	}
	
	public void visibleSetting(Parameter _parameter){
		this.setVisible(true);
		this.now_parameter = _parameter;
	}

	public void setParameterWindow(){
		//this.setSize(WWIDTH, WHEIGHT);
		this.setBounds(100, 100, WWIDTH, WHEIGHT);			//親ウィンドウの表示位置、サイズに合わせて表示するように設定する
		addWindowListener(new MyWindowAdapter());

		GridBagLayout layout = new GridBagLayout();
		this.setLayout(layout);
		GridBagConstraints gbc = new GridBagConstraints();

		//背景色
		gbc.insets = new Insets(5,5,5,5);
		gbc.gridx=0;gbc.gridy=0;gbc.anchor=GridBagConstraints.EAST;		//ラベル位置の設定
		Label label1 = new Label("Background Color:   ");				//ラベル名
		layout.setConstraints(label1, gbc);								//レイアウトにラベル位置の設定
		Choice back_color = new Choice();								//
		back_color.add(getColorName(now_parameter.back_color));
		for(int i=0; i<color.length; i++){								//
			back_color.add(color[i]);						//チョイスにリストを追加
		}
		back_color.addItemListener(new BackColorItemListener());		//チョイスにリスナーを付加
		gbc.gridx=1;gbc.gridy=0;gbc.anchor=GridBagConstraints.WEST;		//チョイス位置の設定
		layout.setConstraints(back_color, gbc);							//レイアウトにチョイス位置の設定

		//文字色
		gbc.gridx=0;gbc.gridy=1;gbc.anchor=GridBagConstraints.EAST;
		Label label2 = new Label("Font Color:   ");
		layout.setConstraints(label2, gbc);
		Choice font_color = new Choice();
		font_color.add(getColorName(now_parameter.font_color));
		for(int i=0; i<color.length; i++){
			font_color.add(color[i]);
		}
		font_color.addItemListener(new FontColorItemListener());
		gbc.gridx=1;gbc.gridy=1;gbc.anchor=GridBagConstraints.WEST;
		layout.setConstraints(font_color, gbc);

		//フォントサイズ
		gbc.gridx=0;gbc.gridy=2;gbc.anchor=GridBagConstraints.EAST;
		Label label3 = new Label("Font Size:   ");
		layout.setConstraints(label3, gbc);
		Choice font_size = new Choice();
		font_size.add(String.valueOf(now_parameter.font_multi));
		for(int i=1; i<MAXFONTSIZE; i++){
			font_size.add(String.valueOf(i/10.0));
		}
		font_size.addItemListener(new FontSizeItemListener());
		gbc.gridx=1;gbc.gridy=2;gbc.anchor=GridBagConstraints.WEST;
		layout.setConstraints(font_size, gbc);
		
		//フォント
		gbc.gridx=0;gbc.gridy=3;gbc.anchor=GridBagConstraints.EAST;
		Label label4 = new Label("Font:   ");
		layout.setConstraints(label4, gbc);
		Choice font = new Choice();
		font.add(now_parameter.font_name);
		for(int i=0; i<fonts.length; i++){
			font.add(fonts[i]);
		}
		font.addItemListener(new FontItemListener());
		gbc.gridx=1;gbc.gridy=3;gbc.anchor=GridBagConstraints.WEST;
		layout.setConstraints(font, gbc);		

		//ボタンの実装
		gbc.insets = new Insets(50,10,10,10);
		gbc.gridx=2;gbc.gridy=5;gbc.anchor=GridBagConstraints.SOUTH;
		Button button1 = new Button("Default");
		button1.addActionListener(new ButtonActionListener());
		layout.setConstraints(button1, gbc);

		gbc.gridx=3;gbc.gridy=5;gbc.anchor=GridBagConstraints.SOUTH;
		Button button2 = new Button("OK");
		button2.addActionListener(new ButtonActionListener());
		layout.setConstraints(button2, gbc);

		gbc.gridx=4;gbc.gridy=5;gbc.anchor=GridBagConstraints.SOUTH;
		Button button3 = new Button("Cancel");
		button3.addActionListener(new ButtonActionListener());
		layout.setConstraints(button3, gbc);


		this.add(label1);
		this.add(back_color);
		this.add(label2);
		this.add(font_color);
		this.add(label3);
		this.add(font_size);
		this.add(label4);
		this.add(font);
		this.add(button1);
		this.add(button2);
		this.add(button3);
		//this.setVisible(true);
	}

	////////内部クラスとしてチョイスのアイテムリスナーとボタンのアクションリスナーを持つ/////////
	//背景色のリスナー
	class BackColorItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			System.out.println(e.getItem().toString());
			edit_parameter.back_color = getColorObject(e.getItem().toString());

		}

	}
	//フォントカラーのリスナー
	class FontColorItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			System.out.println(e.getItem().toString());
			edit_parameter.font_color = getColorObject(e.getItem().toString());
		}

	}
	//フォントのリスナー
	class FontItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			System.out.println(e.getItem().toString());
			edit_parameter.font_name = e.getItem().toString();
		}

	}
	//フォントサイズのリスナー
	class FontSizeItemListener implements ItemListener {
		@Override
		public void itemStateChanged(ItemEvent e) {
			System.out.println(e.getItem().toString());
			edit_parameter.font_multi = Double.parseDouble(e.getItem().toString());
		}

	}

	//ボタンアクションリスナー
	class ButtonActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			//押されたボタンに応じて実行切り替え
			System.out.println(e.getActionCommand());
			if(e.getActionCommand()=="OK"){
				//設定値を現在のParameterに設定
				now_parameter.back_color 	= 	edit_parameter.back_color;
				now_parameter.font_color 	= 	edit_parameter.font_color;
				now_parameter.font_multi 	= 	edit_parameter.font_multi;
				now_parameter.font_name 	= 	edit_parameter.font_name;
				crearEditParameter();
				setVisible(false);
			}else if(e.getActionCommand()=="Cancel"){
				//何もしないで非表示
				crearEditParameter();
				setVisible(false);
			}else if(e.getActionCommand()=="Default"){
				//初期値を現在のParameterに設定
				now_parameter.back_color 	= 	ini_parameter.back_color;
				now_parameter.font_color 	= 	ini_parameter.font_color;
				now_parameter.font_multi 	= 	ini_parameter.font_multi;
				now_parameter.font_name 	=	ini_parameter.font_name;
				crearEditParameter();
				setVisible(false);
			}

		}
	}
	
	public void crearEditParameter(){
		edit_parameter.back_color 	= 	ini_parameter.back_color;
		edit_parameter.font_color 	= 	ini_parameter.font_color;
		edit_parameter.font_multi 	= 	ini_parameter.font_multi;
		edit_parameter.font_name 	=	ini_parameter.font_name;
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
	public String getColorName(Color color){
		if(color.equals(Color.black))		return "BLACK";
		else if(color.equals(Color.white))	return "WHITE";
		else if(color.equals(Color.red))	return "RED";
		else if(color.equals(Color.green))	return "GREEN";
		else if(color.equals(Color.blue))	return "BLUE";
		else if(color.equals(Color.yellow))	return "YELLOW";
		else if(color.equals(Color.pink))	return "PINK";
		else if(color.equals(Color.cyan))	return "CYAN";
		else{return "BLACK";}
	}
	
	

	class MyWindowAdapter extends WindowAdapter {
	    public void windowClosing(WindowEvent e) {
	    	setVisible(false);
	    }
	}




	//


}