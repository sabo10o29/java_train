package interpret;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JTextField;

//コンストラクタの引数設定用ウィンドウ

public class SubArgListDialog extends JDialog{

	SubArgListPanel subpanel;
	
	public SubArgListDialog(java.lang.reflect.Type[] types) {
		super();
		subpanel = new SubArgListPanel(types);
		this.add(subpanel);
		this.setModal(true);
	}
	
	public Object[] getPram(){
		return subpanel.getParam();
	}
	
	//ダイアログウィンドウ用のリストパネル
	//サブウィンドウで開いた場合には終了処理時に閉じる
	//メインパネル内の場合には次へを実行するように促す
	public class SubArgListPanel extends ArgListPanel{

		public SubArgListPanel(java.lang.reflect.Type[] typee) {
			super(typee);
		}
		
		public void finishAction(){
//			setDefaultCloseOperation(HIDE_ON_CLOSE);
			dispose();
		}
		
		
	}
	
}
