package gui.ex11;
import java.awt.*;
import java.awt.event.*;


//ウィンドウ画面の設定用クラス
//ウィンドウに対する操作を個々で指定する予定
public class ClockWindowAdapter extends WindowAdapter{
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
	
	
}
