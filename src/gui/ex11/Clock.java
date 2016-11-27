package gui.ex11;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.io.BufferedInputStream;
import java.io.FileInputStream;

public class Clock extends Frame{
	//static final String path = new File(".").getAbsoluteFile().getParent();		//絶対パス
	static final String currentDir = "./src/gui/ex11/";		//カレントディレクトリ
	static final String fontname = "a.ttf";			//フォント
	static final int frameWidth = 600;						//ウィンドウサイズ
	static final int frameHeight = 300;						//
	private Graphics graphics;								//描画用グラッフィクスクラス
	private Font font;										//時計用フォントクラス

	
	public static void main(String[] args) {
		//時計クラスを実行
		Clock clock = new Clock(frameWidth,frameHeight);
	}
	
	Clock(int _frameWidth, int _frameHeight){
		this.setSize(_frameWidth, _frameHeight);
		this.addWindowListener(new ClockWindowAdapter());
		//フレームの可視化（可視化を行わないとgetGraphicsできないため前で実行）
		this.setVisible(true);
		//現在のグラフィックスを取得
		this.graphics = this.getGraphics();
		while(true){
			try {
				//repaintをおこなうことで、背景色で初期化した後、paintを実行する
				this.repaint();
				//1秒間スリープ
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally{
				
			}
		}
	}
	
	//コンポーネントクラスのpaintメソッドをオーバーライド
	public void paint(Graphics _graphics){
		//現在のグラッフィクスを取得
		Graphics2D g2 = (Graphics2D)_graphics;
		//現在の時刻を取得し、変数に代入
		Calendar calendar = Calendar.getInstance();
		int year        = calendar.get(Calendar.YEAR);
		int month       = calendar.get(Calendar.MONTH);
		int day         = calendar.get(Calendar.DAY_OF_MONTH);
		int hour        = calendar.get(Calendar.HOUR_OF_DAY);
		int minute      = calendar.get(Calendar.MINUTE);
		int second      = calendar.get(Calendar.SECOND);
		//現在の時刻から文字列を作成
		String str = tenDigitString(hour)+":"+tenDigitString(minute)+":"+tenDigitString(second);
		//時刻をウィンドウの中央に配置
		drawStringCenter(g2,str);
	}
	//10桁のフォントに変更
	//1桁の場合には10桁に変更する
	private String tenDigitString(int _number){
		String str = String.valueOf(_number);
		//10で割ったときに商が0の場合には先頭に0をつける
		if((int)_number/10 == 0){
			str = "0" + str;
		}
		return str;		
	}
	//フォントを読みこんでフォントのオブジェクトを返すメソッド
	private Font readFont(String filename){
		Font font = null;
        InputStream is = null;
        
        System.out.println("Load font: "+ filename);
        try {
        	InputStream myStream = new BufferedInputStream(new FileInputStream(filename));
        	font = Font.createFont(Font.TRUETYPE_FONT, myStream);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
        return font;
	}
	private void drawStringCenter(Graphics2D _g, String _str){
		//フォントの大きさを設定（幅の1/5）
		this.font = new Font("Arial", Font.BOLD, (int)(this.getSize().width/5));
		_g.setFont(this.font);
		//ベースライン情報の取得
		FontMetrics fm = _g.getFontMetrics();
		Rectangle rectText = fm.getStringBounds(_str, _g).getBounds();
		//表示位置の計算
		int _height = (int)(this.getSize().getHeight()/2)+(fm.getMaxAscent()/2);
		int _width = (int)(this.getSize().getWidth()-rectText.width)/2;
		//描画
		_g.drawString(_str, _width, _height);
	}
	
	
}

