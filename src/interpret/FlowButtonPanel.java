package interpret;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * ボタンを表示するクラス
 * ステップの遷移が可能かどうかを判定し、可能な場合にが適切に処理を行う
 * @author YoshikazuMurase
 *
 */
public class FlowButtonPanel extends JPanel{

	private final StepMenuPanel smp;
	private final MainBasePanel mbp;
	private int step = 0;
	
	JButton btNext = new JButton("Next");
	JButton btBack = new JButton("Back");
	JButton btCancel = new JButton("Cancel");
	
	FlowButtonPanel(StepMenuPanel _smp, MainBasePanel _mbp){
		
		smp = _smp;
		mbp = _mbp;
				
		btNext.addActionListener(new ButtonActionListener());
		btBack.addActionListener(new ButtonActionListener());
		btCancel.addActionListener(new ButtonActionListener());
		
		//ボタンを貼り付けるパネルの作成
		FlowLayout layout = new FlowLayout();
		layout.setAlignment(FlowLayout.RIGHT);
		this.setLayout(layout);
		//パネルにボタンを追加
		this.add(btBack);
		this.add(btNext);
		this.add(btCancel);
		
		setButtonEnable();
	}
	
	/**
	 * 遷移ボタンリスナー
	 * next：現在のパネルのnextHandlerを呼び出した後に、次のパネルの初期化メソッドを呼び出し、次のパネルへ移動
	 * back：現在のパネルの初期化ボタンを呼び出し、前のパネルへ移動
	 * @author YoshikazuMurase
	 *
	 */
	class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	
        	System.out.println(e.getActionCommand());
        	if(e.getActionCommand()=="Next"){	//次へが押された場合のアクション
        		nextSetup();
        	}else if(e.getActionCommand()=="Back"){	//前へが押された場合のアクション
        		backSetup();
        	}else if(e.getActionCommand()=="Cancel"){
        		System.exit(0);
        	}else{
        		
        	}
        	setButtonEnable();
        }
		
		//次のボタンへ遷移するための処理
		public void nextSetup(){
			if(mbp.getNowPanel().nextHandler()){	//正しい入力がされているかチェック
    			step++;
    			mbp.getPanel(step).init();	//次のパネルの初期化処理
    			smp.nextStep();
    			mbp.nextStep();
    		}
		}
		//前のボタンへ遷移するための処理
		public void backSetup() {
			mbp.getPanel(step).clear();		//現在のパネルのクリア処理
			step--;
    		smp.backStep();
    		mbp.backStep();
		}
        
    }
	
	public void setButtonEnable() {
		if (step == 0) {
			btBack.setEnabled(false);
			btNext.setEnabled(true);
		} else if (step == 4) {
			btBack.setEnabled(true);
			btNext.setEnabled(false);
		} else {
			btBack.setEnabled(true);
			btNext.setEnabled(true);
		}
	}
	
	
}
