package jpl.ch16.gui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

public class MainBasePanel extends JPanel{
	//
	private static final int STEP = 5;
	//現在のステップ数
	private int step = 0;
	//ステップごとのパネルを保持する
	private JPanel[] steppanel = new JPanel[5];
	
	//JPanel changepanel = new JPanel();
	
	
	
	MainBasePanel(){
		this.setBorder(new BevelBorder(BevelBorder.LOWERED));
		
		CardLayout cl = new CardLayout();
		this.setLayout(cl);
		
		steppanel[0] = new Step1Panel();
		steppanel[1] = new Step2Panel();
		steppanel[2] = new Step3Panel();
		steppanel[3] = new Step4Panel();
		steppanel[4] = new Step5Panel();
		
		for(int i=0; i<STEP;i++){
			this.add(steppanel[i],Integer.toString(i));
		}
		
	}
	
	public void nextStep(){
		if(step!=4){
			step++;
			changeStepTitle();
		}else{
			
		}
	}
	
	public void backStep(){
		if(step!=0){
			step--;
			changeStepTitle();
		}else{
			
		}
	}
	
	private final void changeStepTitle(){
		CardLayout cl = (CardLayout)(this.getLayout());
		cl.show(this,Integer.toString(step));
	}
	
}
