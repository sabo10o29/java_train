package gui.ex14;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

//課題1-4
//Settingを押すと設定ウィンドウが表示される
//設定ウィンドウで、フォント、フォントサイズ、背景、文字色を設定可能
//設定を保存しておき、OKボタンが押された場合にのみ変更を反映する
//キャンセルボタンが押された場合には、変更を反映しない
//各設定はドロップダウンリストから選択できるようにする

//サブウィンドウの大きさとレイアウト
//ウィンドウの位置を正確に保存する

public class Clock extends Frame implements MouseListener{
	
	private Font font;										//時計用フォントクラス
	private Image buf;										//描画用バッファ
	
	static final int frameWidth 	= 1000;					//初期ウィンドウサイズ
	static final int frameHeight 	= 500;					//
	
	static Parameter parameter = new Parameter();			//時計のパラメータ保存
	static final PrefsParameter savedata = new PrefsParameter();
	
	private double wx;
	private double wy;

	//メインクラス
	public static void main(String[] args) {
		//時計クラスを実行
		Clock clock = new Clock(frameWidth,frameHeight);
	}
	
	//コンストラクターウィンドウを作成し、クロックを動作
	Clock(int _frameWidth, int _frameHeight){
		Runtime.getRuntime().addShutdownHook( new Shutdown()); 		//シャットダウンフックを登録する
		try {
			parameter = savedata.load();
			System.out.println("Loading successfull");
		} catch (ClassNotFoundException | IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		this.setBounds((int)parameter.window_x, (int)parameter.window_y,(int)parameter.width, (int)parameter.height);
		this.addWindowListener(new ClockWindowAdapter());
		this.addMouseListener(this);
		//メニューバーの作成
		this.initMenuBar();
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
		buf_graphics.setColor(parameter.back_color);				//描画設定//
		buf_graphics.fillRect(0, 0, getSize().width, getSize().height);		//バッファ描画処理//
		//フォントの大きさを設定（ウィンドウ幅の1/5を基準に設定）
		int fontsize = (int)(parameter.font_multi*this.getSize().width/5);
		this.font = new Font(parameter.font_name, Font.BOLD, fontsize);
		buf_graphics.setFont(this.font);							//描画設定//
		//ベースライン情報の取得
		FontMetrics fm = buf_graphics.getFontMetrics();
		Rectangle rectText = fm.getStringBounds(_str, buf_graphics).getBounds();
		//表示位置の計算
		int _height = (int)(this.getSize().getHeight()/2)+(fm.getMaxAscent()/2);
		int _width = (int)(this.getSize().getWidth()-rectText.width)/2;
		//描画
		buf_graphics.setColor(parameter.font_color);				//描画設定//
		buf_graphics.drawString(_str, _width, _height);				//バッファ描画処理//
		_g.drawImage(this.buf, 0, 0, this);							//バッファをグラフィックスに書き込む
	}
	
	/////////////////////メニューバーの設定///////////////////////
	//メニューごとのアクションリスナー
	//めんどくさいのでメニューごとにアクションリスナーのインナーサブクラスを作成
	//設定ウィンドウを表示するためのリスナー
	class SettingActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	ParameterWindow pwindow = new ParameterWindow(parameter);	//設定サブウィンドウのインスタンス
            //pwindow.visibleSetting(parameter);
            System.out.println(e.getActionCommand());
        }
    }

	//メニューバーの初期化メソッド
	public final void initMenuBar(){
		
		//メニューバーに表示するカラムの作成
		MenuBar menuBar = new MenuBar();
		// [Property]
        Menu menuView = new Menu("Property");
        menuBar.add(menuView);
        
        //設定ウィンドウ表示用
        Menu settings = new Menu("Settings");
        settings.addActionListener(new SettingActionListener());
        menuView.add(settings);
        setMenuBar(menuBar);    
	}
	
	class Shutdown extends Thread{
	    public void run(){
	    	//ここに、アプリケーション終了時に実施する処理を追加します
	    	try {
	    		parameter.window_x 	= wx;
	    		parameter.window_y 	= wy;
	    		parameter.width 	= getSize().getWidth();
	    		parameter.height 	= getSize().getHeight();
	    		savedata.save(parameter);
	    	} catch (IOException e) {
	    		e.printStackTrace();
	    	}
	    	System.out.println("Closing window");
	    }
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println(e.getSource());
		Point point = e.getPoint();		//クリックした際の座標取得
		wx = point.getX();
		wy = point.getY();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		//System.out.println(e.getSource());
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		//System.out.println(e.getSource());
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		//System.out.println(e.getSource());
		
	}
	
}

