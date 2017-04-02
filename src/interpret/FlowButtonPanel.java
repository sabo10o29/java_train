package interpret;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class FlowButtonPanel extends JPanel{

	private final StepMenuPanel smp;
	private final MainBasePanel mbp;
	private int step = 0;
	
	//ボタンの追加：次へ、戻る、キャンセルボタンを設定する
	JButton btNext = new JButton("Next");
	JButton btBack = new JButton("Back");
	JButton btCancel = new JButton("Cancel");
	
	FlowButtonPanel(StepMenuPanel _smp, MainBasePanel _mbp){
		
		//
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
	
	//現在のパネルの情報を次のパネルに引き渡すメソッド
	//前のステップの情報を渡された後に初期化処理を行う
	public void delivParam(int nextStep){
		if(nextStep!=0){
			StepBasePanel now = mbp.getPanel(nextStep-1);
			StepBasePanel next = mbp.getPanel(nextStep);
			now.nextHandler();						//
			next.setBfParameter(now.getMap());
			invokeInit(next);
		}
	}
	
	public void clearPanel(int bfStep){
		StepBasePanel now = mbp.getPanel(bfStep+1);
		StepBasePanel back = mbp.getPanel(bfStep);
		now.clear();
	}
	
	public void invokeInit(StepBasePanel panel){
		panel.init();
	}
	
	class ButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	System.out.println(e.getActionCommand());
        	if(e.getActionCommand()=="Next"){
        		step++;
        		delivParam(step);
        		smp.nextStep();
        		mbp.nextStep();
        		
        	}else if(e.getActionCommand()=="Back"){
        		step--;
        		clearPanel(step);
        		smp.backStep();
        		mbp.backStep();
        	}else if(e.getActionCommand()=="Cancel"){
        		System.exit(0);
        	}else{
        		
        	}
        	setButtonEnable();
        }
    }
	
	public void setButtonEnable(){
		if(step==0){
			btBack.setEnabled(false);
			btNext.setEnabled(true);
		}else if(step==4){
			btBack.setEnabled(true);
			btNext.setEnabled(false);
		}else{
			btBack.setEnabled(true);
			btNext.setEnabled(true);
		}
	}
	
}
