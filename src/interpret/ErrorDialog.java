package interpret;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.awt.Color;

/**
 * エラーをユーザーに表示するインターフェース
 * @author YoshikazuMurase
 *
 */
public interface ErrorDialog{
	
	/**
	 * 例外が発生した場合にポップアップウィンドウでエラーを表示する
	 * @param messagem
	 */
	public void showErrorDialog(String message);
	
}
