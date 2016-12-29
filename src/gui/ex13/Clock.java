package gui.ex13;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

//ペイントメソッドが呼びたされたときに一瞬すべてクリアされる→チラツキが発生
//全画面クリアしないようにする&事前に次の画面を用意しておき、切り替える。

public class Clock extends Window implements MouseListener, MouseMotionListener{
	//フォント・背景設定用カラー一覧
	static final String[] color = {"BLACK","WHITE","RED","GREEN","BLUE","YELLOW","PINK","CYAN"};
	private Font font;								//時計用フォントクラス
	private Image buf;								//描画用バッファ
	
	static final int frameWidth 	= 600;			//初期ウィンドウサイズ
	static final int frameHeight 	= 300;			//
	static final int MAXFONTSIZE 	= 13;			//
	private String 	font_name 	= "Arial";			//初期フォント
	private double 	font_multi 	= 1;				//初期フォントサイズ（倍率）
	private Color 	font_color 	= Color.black;		//初期フォントカラー
	private PopupMenu popup = initPopupMenu();		//ポップアップウィンドウ
	private double fpoint_x;							//クリック時の座標
	private double fpoint_y;							//
	
	private String old_str = getStringClock();////
	private int fade = 0;

	//メインクラス
	public static void main(String[] args) {
		//時計クラスを実行
		Clock clock = new Clock(frameWidth,frameHeight);
	}
	
	//コンストラクターウィンドウを作成し、クロックを動作
	Clock(int _frameWidth, int _frameHeight){
		super(new Frame());
		this.setSize(_frameWidth, _frameHeight);
		this.addWindowListener(new ClockWindowAdapter());
		//Windowにマウスリスナーを追加
		addMouseListener(this);
		addMouseMotionListener(this);
		//初期画面のサイズをバッファに設定
		this.buf = createImage(getSize().width,getSize().height);
		//フレームの可視化（可視化を行わないとgetGraphicsできないため前で実行）
		this.setVisible(true);
		//時計の描画
		while(true){
			try {
				//repaintをおこなうことで、背景色で初期化した後、paintを実行する
				this.repaint();
				//1秒間スリープ
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				
			}
		}
	}
	
	//コンポーネントクラスのpaintメソッドをオーバーライド
	public void paint(Graphics _graphics){
		//現在のグラッフィクスと時刻を取得
		Graphics2D g2 = (Graphics2D)_graphics;
		String str = getStringClock();
		//時刻をウィンドウの中央に配置
		drawStringCenter(g2,str);
	}
	
	//時刻を文字列に変換するメソッド
	private final String getStringClock(){
		//現在の時刻を取得し、変数に代入
		Calendar calendar = Calendar.getInstance();
		int year        = calendar.get(Calendar.YEAR);
		int month       = calendar.get(Calendar.MONTH);
		int day         = calendar.get(Calendar.DAY_OF_MONTH);
		int hour        = calendar.get(Calendar.HOUR_OF_DAY);
		int minute      = calendar.get(Calendar.MINUTE);
		int second      = calendar.get(Calendar.SECOND);
		//現在の時刻から文字列を作成
		return tenDigitString(hour)+":"+tenDigitString(minute)+":"+tenDigitString(second);
	}
	
	//10桁のフォントに変更するメソッド
	//1桁の場合には10桁に変更する
	private final String tenDigitString(int _number){
		String str = String.valueOf(_number);
		//10で割ったときに商が0の場合には先頭に0をつける
		if((int)_number/10 == 0){
			str = "0" + str;
		}
		return str;		
	}
	
	public void update(Graphics g){
		paint(g);
	}
	
	//常に画面の中央に時計をセットするメソッド
	private final void drawStringCenter(Graphics2D _g, String _str){
		//画面のサイズをバッファに設定
		this.buf = createImage(getSize().width,getSize().height);
		//バッファのグラフィックスを取り出し、そこに描画を書き込む
		Graphics buf_graphics = this.buf.getGraphics();	
		//フォントの大きさを設定（ウィンドウ幅の1/5を基準に設定）
		int fontsize = (int)(this.font_multi*this.getSize().width/5);
		this.font = new Font(this.font_name, Font.BOLD, fontsize);
		buf_graphics.setFont(this.font);							//描画処理//
		//ベースライン情報の取得
		FontMetrics fm = buf_graphics.getFontMetrics();
		Rectangle rectText = fm.getStringBounds(_str, buf_graphics).getBounds();
		//表示位置の計算
		int _height = (int)(this.getSize().getHeight()/2)+(fm.getMaxAscent()/2);
		int _width = (int)(this.getSize().getWidth()-rectText.width)/2;
		//描画
		buf_graphics.setColor(this.font_color);						//描画処理//
		buf_graphics.drawString(_str, _width, _height);				//描画処理//
		_g.drawImage(this.buf, 0, 0, this);							//バッファをグラフィックスに書き込む
		this.old_str = _str;
	}
	
	/////////////////////メニューバーの設定///////////////////////
	//メニューごとのアクションリスナー
	//めんどくさいのでメニューごとにアクションリスナーのインナーサブクラスを作成
	//フォントカラー
	class FontColorActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            font_color = getColorObject(e.getActionCommand());
        }
    }
	//フォントサイズ
	class FontSizeActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	//既定サイズからの倍率を変更
            font_multi = Double.parseDouble(e.getActionCommand());
        }
    }
	//フォント
	class FontActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	//フォントの変更
        	font_name = e.getActionCommand();
        }
    }
	//背景色
	class BackgroundColorActionListener implements ActionListener {
	    public void actionPerformed(ActionEvent e) {
	    	//次クラスFrameの背景色を変更
	        setBackground(getColorObject(e.getActionCommand()));
	    }
	}	

	//メニューバーの初期化メソッド
	public final PopupMenu initPopupMenu(){
		//使用できるフォントネームの取得
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String fonts[] = ge.getAvailableFontFamilyNames();
		
		PopupMenu _popup = new PopupMenu();
		
        ///////////背景色/////////////
        // [Property]-[Background color]
        Menu backcolor = new Menu("Background color");
        _popup.add(backcolor);
        // [Property]-[Background color]-[color]
        for(int i=0; i<color.length; i++){
        	MenuItem allcolor = new MenuItem(color[i]);
        	allcolor.addActionListener(new BackgroundColorActionListener());
        	backcolor.add(allcolor);
        }
        //////////////////////////////////
        
        ///////////フォントカラー/////////////
        // [Property]-[Font color]
        Menu fontcolor = new Menu("Font color");
        _popup.add(fontcolor);
        // [Property]-[Font color]-[color]
        for(int i=0; i<color.length; i++){
        	 MenuItem allcolor = new MenuItem(color[i]);
        	 allcolor.addActionListener(new FontColorActionListener());
             fontcolor.add(allcolor);
        }
        //////////////////////////////////
        
        ///////////フォント/////////////
        // [Property]-[Font]
        Menu font = new Menu("Font");
        _popup.add(font);
        // [Property]-[Font]-[Font]
        for(int i=0; i<fonts.length; i++){
        	 MenuItem allfont = new MenuItem(fonts[i]);
        	 allfont.addActionListener(new FontActionListener());
             font.add(allfont);
        }
        //////////////////////////////////
        
        ////////////フォントサイズ/////////////
        // [Property]-[Font size]
        Menu fontsize = new Menu("Font size");
        _popup.add(fontsize);
        // [Property]-[Font size]-[Size]
        for(int i=1; i<MAXFONTSIZE; i++){
        	 MenuItem size = new MenuItem(String.valueOf(i/10.0));
        	 size.addActionListener(new FontSizeActionListener());
             fontsize.add(size);
        }
        ///////////////////////////////////
        add(_popup);
        return _popup;
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
	
	public Color getFadeColor(String now_str){
		int a = 255 - 10*this.fade;
		Color fade_color = new Color(font_color.getRed(),font_color.getGreen(),font_color.getBlue(),a); 
		//Color fade_color = font_color;
		if(now_str.equals(this.old_str)){
			this.fade++;
		}else{
			this.fade=0;
		}
		return fade_color;
	}	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		int btn = e.getButton();		//クリックの状態取得
		Point point = e.getPoint();		//クリックした際の座標取得
		
		if (btn == MouseEvent.BUTTON1){
		    //System.out.println("左ボタンクリック");
		  }else if (btn == MouseEvent.BUTTON2){
		    //System.out.println("中ボタンクリック");
		  }else if (btn == MouseEvent.BUTTON3){
		    //ポップアップを表示
		    this.popup.show(e.getComponent(), point.x, point.y);
		  }
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		//マウスが押されたときの場所を保存
		this.fpoint_x = e.getX();
		this.fpoint_y = e.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		//クリック時の座標と現在の座標から、ウィンドウの表示位置を決定
		//現在の位置　-　クリック時の座標
		double x = MouseInfo.getPointerInfo().getLocation().getX() - this.fpoint_x;
		double y = MouseInfo.getPointerInfo().getLocation().getY() - this.fpoint_y;
		//ウィンドウにマウスの座標をロケーションとして設定
		this.setLocation((int)x, (int)y);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}

